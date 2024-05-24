package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.QReview;
import ArtBridge.ArtBridgelogin.domain.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.LockModeType;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToUrl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReviewRepository {

    private final EntityManager em;
    private QReview qReview;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        this.qReview = QReview.review;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public ReviewRepository(EntityManager em) {
        this.em = em;
    }

    public Review createReview(Review review) {
        em.persist(review);
        return review;
    }

    public Review readBySeq(Integer seq) {
        return queryFactory
                .selectFrom(qReview)
                .where(qReview.reviewSeq.eq(seq))
                .fetchOne();
    }

    public List<Review> readAll() {
        return queryFactory
                .selectFrom(qReview)
                .fetch();
    }

    public Review updateReview(Integer reviewSeq, Review updatedReview) {
        Review detachedReview = queryFactory
                .selectFrom(QReview.review)
                .where(QReview.review.reviewSeq.eq(reviewSeq))
                .fetchOne();

        if (detachedReview == null) {
            throw new IllegalArgumentException("Review with id " + reviewSeq + " not found");
        }

        Review managedReview = queryFactory
                .selectFrom(QReview.review)
                .where(QReview.review.reviewSeq.eq(reviewSeq))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne();

        if (managedReview == null) {
            throw new IllegalArgumentException("Review with id " + reviewSeq + " not found");
        }

        // Update fields directly
        managedReview.setReviewContent(updatedReview.getReviewContent());
        managedReview.setReviewVisit(updatedReview.getReviewVisit());

        return managedReview;
    }

    public void deleteById(Integer id) {
        Review review = queryFactory
                .selectFrom(qReview)
                .where(qReview.reviewSeq.eq(id))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne();

        if (review == null) {
            throw new EntityNotFoundException("Review with ID " + id + " not found");
        }

        em.remove(review);
    }

}
