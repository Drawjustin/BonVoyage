package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
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
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    public Member create(Member member) {
        em.persist(member);
        return member;
    }

    public List<Member> readAllMembersSorted() {
        return queryFactory
                .selectFrom(qMember)
                .orderBy(qMember.memberName.asc())
                .fetch();
    }



    @Transactional(readOnly = true)
    public Optional<Member> findByMemberIdAndMemberPwd(String memberId, String password) {
        Member member = queryFactory
                .selectFrom(qMember)
                .where(qMember.memberId.eq(memberId).and(qMember.memberPwd.eq(password)))
                .fetchOne();

        return Optional.ofNullable(member);
    }

    public Member readMemberById(String memberId) {
        return queryFactory
                .selectFrom(qMember)
                .where(qMember.memberId.eq(memberId))
                .fetchOne();
    }

    public Member readMemberBySeq(Long memberSeq) {
        return queryFactory
                .selectFrom(qMember)
                .where(qMember.memberSeq.eq(memberSeq))
                .fetchOne();
    }
    @Transactional(readOnly = true)
    public Optional<Member> findByMemberId(String memberId) {
        Member member = queryFactory
                .selectFrom(qMember)
                .where(qMember.memberId.eq(memberId))
                .fetchOne();

        return Optional.ofNullable(member);
    }

    public List<Member> readAll() {
        return queryFactory
                .selectFrom(qMember)
                .fetch();
    }

    public Member updateMember(String memberId, Member updatedMember) {
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
                .where(QMember.member.memberId.eq(memberId))
                .execute();

        if (updatedCount > 0) {
            return queryFactory
                    .selectFrom(QMember.member)
                    .where(QMember.member.memberId.eq(memberId))
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                    .fetchOne();
        } else {
            return null;
        }
    }

    public Member readByName(String name) {
        return queryFactory
                .selectFrom(qMember)
                .where(qMember.memberName.eq(name))
                .fetchOne();
    }

    public void deleteByMemberId(String memberId) {
        queryFactory
                .delete(qMember)
                .where(qMember.memberId.eq(memberId))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .execute();
    }
}
