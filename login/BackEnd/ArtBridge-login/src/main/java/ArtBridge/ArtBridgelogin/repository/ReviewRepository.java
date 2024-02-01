package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {
    private final EntityManager em;
    public Review create(Review review) {
        em.persist(review);
        return review;
    }
//    public Review updateTeamInfo(Review review) {
//        Review newreview = em.find(Review.class, review.getReviewSeq());
//
//
//        return newreview;
//    }

    public Review findOne(Long id) {
        return em.find(Review.class, id);
    }

    public List<Review> findAll() {
        return em.createQuery("SELECT r FROM Review r", Review.class)
                .getResultList();
    }

//    public Review findReviewId(String reviewId) {
//        String jpql = "SELECT r FROM Review r WHERE r.reviewSeq = :reviewId";
//        TypedQuery<Review> query = em.createQuery(jpql, Review.class);
//        query.setParameter("reviewId", reviewId);
//
//        try {
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }

//    public void deleteById(String reviewId) {
//        em.createQuery("DELETE FROM Review r WHERE r.reviewSeq = :reviewId")
//                .setParameter("reviewId", reviewId)
//                .executeUpdate();
//    }
}
