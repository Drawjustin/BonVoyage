package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.QReviewComment;
import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.LockModeType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReviewCommentRepository {

    private final EntityManager em;
    private QReviewComment qReviewComment;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        this.qReviewComment = QReviewComment.reviewComment;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public ReviewCommentRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void create(ReviewComment comment) {
        em.persist(comment);
    }

    @Transactional(readOnly = true)
    public ReviewComment readById(Long seq) {
        return queryFactory
                .selectFrom(qReviewComment)
                .where(qReviewComment.reviewCommentSeq.eq(seq))
                .fetchOne();
    }

    @Transactional(readOnly = true)
    public List<ReviewComment> readAllByReviewId(Long reviewId) {
        return queryFactory
                .selectFrom(qReviewComment)
                .where(qReviewComment.review.reviewSeq.eq(Math.toIntExact(reviewId)))
                .fetch();
    }

    @Transactional(readOnly = true)
    public List<ReviewComment> readAll() {
        return queryFactory
                .selectFrom(qReviewComment)
                .fetch();
    }

    @Transactional
    public ReviewComment update(Long commentSeq, ReviewComment updatedComment) {
        ReviewComment detachedComment = queryFactory
                .selectFrom(QReviewComment.reviewComment)
                .where(QReviewComment.reviewComment.reviewCommentSeq.eq(commentSeq))
                .fetchOne();

        if (detachedComment == null) {
            throw new IllegalArgumentException("ReviewComment with id " + commentSeq + " not found");
        }

        ReviewComment managedComment = queryFactory
                .selectFrom(QReviewComment.reviewComment)
                .where(QReviewComment.reviewComment.reviewCommentSeq.eq(commentSeq))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne();

        if (managedComment == null) {
            throw new IllegalArgumentException("ReviewComment with id " + commentSeq + " not found");
        }

        // Update fields directly
        managedComment.setReviewCommentContent(updatedComment.getReviewCommentContent());

        return managedComment;
    }

    @Transactional
    public void deleteById(Long id) {
        ReviewComment comment = queryFactory
                .selectFrom(qReviewComment)
                .where(qReviewComment.reviewCommentSeq.eq(id))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne();

        if (comment == null) {
            throw new EntityNotFoundException("ReviewComment with ID " + id + " not found");
        }

        em.remove(comment);
    }
}
