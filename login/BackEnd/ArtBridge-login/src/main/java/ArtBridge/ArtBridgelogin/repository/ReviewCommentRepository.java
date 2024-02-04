package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.QOrderDetail;
import ArtBridge.ArtBridgelogin.domain.QReviewComment;
import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewCommentRepository {


    private final EntityManager em;

    private QReviewComment qReviewComment = QReviewComment.reviewComment;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {queryFactory = new JPAQueryFactory(em);}


    public ReviewComment create(ReviewComment reviewComment) {
        em.persist(reviewComment);
        return reviewComment;
    }

    public ReviewComment findOne(Long Commentid) {
        return em.find(ReviewComment.class, Commentid);
    }

    public List<ReviewComment> findAll() {

        List<ReviewComment> reviewComments = queryFactory
                .selectFrom(qReviewComment)
                .fetch();

        return reviewComments;
    }
}
