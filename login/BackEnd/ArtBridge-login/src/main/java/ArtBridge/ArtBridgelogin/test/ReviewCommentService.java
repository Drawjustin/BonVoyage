package ArtBridge.ArtBridgelogin.test;

import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import ArtBridge.ArtBridgelogin.repository.ReviewCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ReviewCommentService {
    @Autowired
    private ReviewCommentRepository reviewCommentRepository;

    //Todo: CREATE
    @Transactional
    public void createReviewComment(ReviewComment reviewComment) {
        reviewCommentRepository.createReviewComment(reviewComment);
    }

    //TODO: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<ReviewComment> readAllReviewComment(Integer reviewSeq) {
        return reviewCommentRepository.readAll(reviewSeq);
    }

    //TODO: UPDATE
    @Transactional
    public ReviewComment updateReviewComment(Long seq, ReviewComment updatedReviewComment) {
        return reviewCommentRepository.updateReviewComment(seq,updatedReviewComment);
    }

    //TODO: DELETE
    @Transactional
    public void deleteReviewComment(Long seq) {
        reviewCommentRepository.deleteReviewComment(seq);
    }
}
