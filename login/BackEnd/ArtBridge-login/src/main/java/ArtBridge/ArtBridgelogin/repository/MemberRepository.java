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

    @Transactional
    public Member create(Member member) {
        em.persist(member);
        return member;
    }

    @Transactional(readOnly = true)
    public Member findOne(Long id){return em.find(Member.class, id);}

    @Transactional(readOnly = true)
    public List<Member> findAll(){
        return queryFactory
                .selectFrom(qMember)
                .fetch();
    }
    @Transactional(readOnly = true)
    public Optional<Member> findMemberById(String memberId) {
        Member member = queryFactory
                .selectFrom(qMember)
                .where(qMember.memberId.eq(memberId))
                .fetchOne();

        return Optional.ofNullable(member);
    }

    @Transactional
    public void updateMember(Long id, String newUsername, String newPassword) {
        // Querydsl 사용하여 해당 ID에 해당하는 Member 조회
        QMember qMember = QMember.member;
        Member member = queryFactory
                .selectFrom(qMember)
                .where(qMember.memberSeq.eq(id))
                .fetchOne();

        // 해당 ID에 해당하는 Member가 없으면 예외 처리
        if (member == null) {
            throw new EntityNotFoundException("Member with ID " + id + " not found");
        }

        // 수정할 필드 업데이트
        member.setMemberName(newUsername);
        member.setMemberPwd(newPassword);

        // 업데이트된 Member를 저장
        em.persist(member);
    }

    @Transactional
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
