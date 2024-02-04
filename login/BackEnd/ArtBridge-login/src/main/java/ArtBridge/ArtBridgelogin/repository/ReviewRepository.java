package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.QReview;
import ArtBridge.ArtBridgelogin.domain.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    public void create(Review review) {
        em.persist(review);
    }

    @Transactional(readOnly = true)
    public Review findById(Integer seq) {

        return queryFactory
                .selectFrom(qReview)
                .where(qReview.reviewSeq.eq(seq))
                .fetchOne();
    }

    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return queryFactory
                .selectFrom(qReview)
                .fetch();
    }

    @Transactional
    public void updateReview(Integer id, String newContent, String newVisit) {
        Review review = queryFactory
                .selectFrom(qReview)
                .where(qReview.reviewSeq.eq(id))
                .fetchOne();

        if (review == null) {
            throw new EntityNotFoundException("Review with ID " + id + " not found");
        }

        review.setReviewContent(newContent);
        review.setReviewVisit(newVisit);

        em.persist(review);
    }

    @Transactional
    public void deleteById(Integer id) {
        Review review = queryFactory
                .selectFrom(qReview)
                .where(qReview.reviewSeq.eq(id))
                .fetchOne();

        if (review == null) {
            throw new EntityNotFoundException("Review with ID " + id + " not found");
        }

        em.remove(review);
    }
}
