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
        reviewCommentRepository.create(reviewComment);
    }

    @Transactional
    public List<ReviewComment> getAllReviews() {
        return reviewCommentRepository.findAll();

    }
}
