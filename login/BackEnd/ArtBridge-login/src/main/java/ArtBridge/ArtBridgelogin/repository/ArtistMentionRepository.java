package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArtistMentionRepository {

    private final EntityManager em;



    @Transactional
    public ArtistMention create(ArtistMention artistMention){
        em.persist(artistMention);
        return artistMention;
    }

    @Transactional(readOnly = true)
    public ArtistMention findOne(Long id){return em.find(ArtistMention.class, id);}

    @Transactional(readOnly = true)
    public List<ArtistMention> findAll(){
        return em.createQuery("select m from ArtistMention m", ArtistMention.class)
                .getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM ArtistMention m WHERE m.artistId = :id")
                .setParameter("artistId", id)
                .executeUpdate();
    }
}
