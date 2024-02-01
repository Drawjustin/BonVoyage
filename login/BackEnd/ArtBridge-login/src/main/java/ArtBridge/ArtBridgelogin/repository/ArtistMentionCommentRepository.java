package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.ArtistMentionComment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArtistMentionCommentRepository {

    private final EntityManager em;

    @Transactional
    public ArtistMentionComment create(ArtistMentionComment artistMentionComment){
        em.persist(artistMentionComment);
        return artistMentionComment;
    }

    @Transactional(readOnly = true)
    public ArtistMentionComment findOne(Long id){return em.find(ArtistMentionComment.class, id);}

    @Transactional(readOnly = true)
    public List<ArtistMentionComment> findAll(){
        return em.createQuery("select m from ArtistMentionComment m", ArtistMentionComment.class)
                .getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM ArtistMentionComment m WHERE m.artistId = :id")
                .setParameter("artistId", id)
                .executeUpdate();
    }
}
