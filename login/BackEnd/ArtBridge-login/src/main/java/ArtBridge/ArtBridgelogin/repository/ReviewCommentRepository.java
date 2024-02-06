package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewCommentRepository {


    private final EntityManager em;

    private QReviewComment qReviewComment = QReviewComment.reviewComment;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {queryFactory = new JPAQueryFactory(em);}

    public ReviewComment findOne(Long Commentid) {
        return em.find(ReviewComment.class, Commentid);
    }
    public List<ReviewComment> findAll(int seq) {

        List<ReviewComment> reviewComments = queryFactory
                .selectFrom(qReviewComment)
                .where(QReview.review.reviewSeq.eq(seq))
                .fetch();

        return reviewComments;
    }
    public ReviewComment createReviewComment(ReviewComment reviewComment) {
        em.persist(reviewComment);
        return reviewComment;
    }
    public ReviewComment updateReviewComment(Long seq, ReviewComment updatedReviewComment) {

        queryFactory
                .update(qReviewComment)
                .where(qReviewComment.member.memberSeq.eq(seq))
                .set(qReviewComment.reviewCommentContent, updatedReviewComment.getReviewCommentContent())
                .execute();

        return queryFactory.selectFrom(qReviewComment)
                .where(qReviewComment.reviewCommentSeq.eq(seq))
                .fetchOne();
    }
    public void deleteReviewComment(Long seq) {
        queryFactory
                .delete(qReviewComment)
                .where(qReviewComment.reviewCommentSeq.eq(seq))
                .execute();
    }


}
