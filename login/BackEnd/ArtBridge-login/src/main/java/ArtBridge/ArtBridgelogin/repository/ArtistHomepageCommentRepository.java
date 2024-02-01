package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.ArtistHomepageComment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ArtistHomepageCommentRepository {

    private final EntityManager em;

    @Transactional
    public ArtistHomepageComment create(ArtistHomepageComment artistHomepageComment){
        em.persist(artistHomepageComment);
        return artistHomepageComment;
    }

    @Transactional(readOnly = true)
    public ArtistHomepageComment findOne(Long id){return em.find(ArtistHomepageComment.class, id);}

    @Transactional(readOnly = true)
    public List<ArtistHomepageComment> findAll(){
        return em.createQuery("select m from ArtistHomepageComment m", ArtistHomepageComment.class)
                .getResultList();
     }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM ArtistHomepageComment m WHERE m.artistId = :id")
                .setParameter("artistId", id)
                .executeUpdate();
    }
}
