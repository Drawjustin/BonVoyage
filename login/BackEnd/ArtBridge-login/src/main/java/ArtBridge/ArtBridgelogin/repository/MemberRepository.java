package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.member.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    @Transactional
    public Member save(Member member) {
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
