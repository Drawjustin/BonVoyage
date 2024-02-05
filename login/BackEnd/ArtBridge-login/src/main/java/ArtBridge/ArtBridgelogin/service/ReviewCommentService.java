package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import ArtBridge.ArtBridgelogin.repository.ReviewCommentRepository;
import ArtBridge.ArtBridgelogin.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ReviewCommentService {
    @Autowired
    private ReviewCommentRepository reviewCommentRepository;

    @Transactional
    public ReviewComment getReviewById(Long reviewCommentId) {
        return reviewCommentRepository.findOne(reviewCommentId);

    }

    // 리뷰 등록
    @Transactional
    public void createReview(ReviewComment reviewComment) {
        reviewCommentRepository.createReviewComment(reviewComment);
    }

    @Transactional
    public List<ReviewComment> getAllReviewComment(int seq) {
        return reviewCommentRepository.findAll(seq);

    }

    public ReviewComment findOne(Long seq) {
        return reviewCommentRepository.findOne(seq);
    }

    public ReviewComment createReviewComment(ReviewComment reviewComment) {
        return reviewCommentRepository.createReviewComment(reviewComment);
    }

    public ReviewComment updateReviewComment(Long seq, ReviewComment updatedReviewComment) {
        return reviewCommentRepository.updateReviewComment(seq,updatedReviewComment);
    }

    public void deleteReviewComment(Long seq) {
        reviewCommentRepository.deleteReviewComment(seq);
    }
}
