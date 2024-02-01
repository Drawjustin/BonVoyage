package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewCommentRepository {
    private final EntityManager em;
    public ReviewComment create(ReviewComment reviewComment) {
        em.persist(reviewComment);
        return reviewComment;
    }

    public ReviewComment findOne(Long Commentid) {
        return em.find(ReviewComment.class, Commentid);
    }

    public List<ReviewComment> findAll() {
        return em.createQuery("SELECT r FROM ReviewComment r", ReviewComment.class)
                .getResultList();
    }
}
