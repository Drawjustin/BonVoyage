package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    @Transactional
    public Member create(Member member) {
        em.persist(member);
        return member;
    }

    @Transactional(readOnly = true)
    public Member findOne(Long id){return em.find(Member.class, id);}

    @Transactional(readOnly = true)
    public List<Member> findAll(){
        return em.createQuery("select from Member m", Member.class)
                .getResultList();
    }
    @Transactional(readOnly = true)
    public Member findMemberId(String memberId) {
        String jpql = "SELECT a FROM Member a WHERE a.MemberId = :memberId";
        TypedQuery<Member> query = em.createQuery(jpql, Member.class);
        query.setParameter("memberId", memberId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // 해당 조건에 맞는 결과가 없을 경우
        }
    }
    @Transactional(readOnly = true)
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.memberName = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM Member m WHERE m.memberSeq = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
