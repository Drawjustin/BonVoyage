package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.*;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.QMember;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    private QMember qMember = QMember.member;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {queryFactory = new JPAQueryFactory(em);}

    public Member create(Member member) {
        em.persist(member);
        return member;
    }

    public Member readOne(Long id){return em.find(Member.class, id);}

    public List<Member> readAll(){
        return queryFactory
                .selectFrom(qMember)
                .fetch();
    }
    public ResponseEntity<?> readMemberById(String memberId) {
        Member member = queryFactory
                .selectFrom(qMember)
                .where(qMember.memberId.eq(memberId))
                .fetchOne();

        if (member != null) {
            return ResponseEntity.ok().body(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Member updateMember(String id, Member updatedMember) {
        Member existingMember = queryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.memberId.eq(id))
                .fetchOne();

        if (existingMember == null) {
            // 예외 처리 또는 적절한 로직 추가
            throw new RuntimeException("Member with id " + id + " not found");
        }

        long updatedCount = queryFactory
                .update(QMember.member)
                .set(QMember.member.memberName, updatedMember.getMemberName())
                .set(QMember.member.memberPwd, updatedMember.getMemberPwd())
                .set(QMember.member.memberNickname, updatedMember.getMemberNickname())
                .set(QMember.member.memberEmail, updatedMember.getMemberEmail())
                .set(QMember.member.memberContact, updatedMember.getMemberContact())
                .set(QMember.member.memberPoint, updatedMember.getMemberPoint())
                .set(QMember.member.memberIsDeleted, updatedMember.isMemberIsDeleted())
                .set(QMember.member.memberDeletedDate, updatedMember.getMemberDeletedDate())
                .set(QMember.member.memberCreatedDate, updatedMember.getMemberCreatedDate())
                .where(QMember.member.memberId.eq(id))
                .execute();

        if (updatedCount > 0) {
            return queryFactory
                    .selectFrom(QMember.member)
                    .where(QMember.member.memberId.eq(id))
                    .fetchOne();
        } else {
            // 예외 처리 또는 적절한 로직 추가
            throw new RuntimeException("Failed to update Member with id " + id);
        }
    }

    public void deleteById(Long id) {
        // Querydsl 사용하여 해당 ID에 해당하는 Member 조회
        Member member = queryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.memberSeq.eq(id))
                .fetchOne();

        // 해당 ID에 해당하는 Member가 없으면 예외 처리
        if (member == null) {
            throw new EntityNotFoundException("Member with ID " + id + " not found");
        }

        // 찾은 Member를 삭제
        em.remove(member);
    }

}
