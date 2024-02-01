package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import ArtBridge.ArtBridgelogin.repository.ReviewCommentRepository;
import ArtBridge.ArtBridgelogin.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewCommentService {
    @Autowired
    private ReviewCommentRepository reviewCommentRepository;
    public ReviewComment getReviewById(Long reviewCommentId) {
        // 데이터베이스에서 reviewId에 해당하는 리뷰 조회 로직을 추가합니다.
        // 예시로 비어있는 Review 객체를 반환합니다.

        return reviewCommentRepository.findOne(reviewCommentId);

    }

    // 리뷰 등록
    public void createReview(ReviewComment reviewComment) {
        // 데이터베이스에 review를 저장하는 로직을 추가합니다.
        reviewCommentRepository.create(reviewComment);
    }

    public List<ReviewComment> getAllReviews() {
        // 데이터베이스에서 모든 리뷰를 조회하는 로직을 추가합니다.
        // 예시로 비어있는 리스트를 반환합니다.
        return reviewCommentRepository.findAll();

    }
}
