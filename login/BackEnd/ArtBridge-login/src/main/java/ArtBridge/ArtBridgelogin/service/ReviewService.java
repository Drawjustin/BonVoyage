package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    public Review getReviewById(Long reviewId) {
        // 데이터베이스에서 reviewId에 해당하는 리뷰 조회 로직을 추가합니다.
        // 예시로 비어있는 Review 객체를 반환합니다.

        return reviewRepository.findOne(reviewId);

    }

    // 리뷰 등록
    public void createReview(Review review) {
        // 데이터베이스에 review를 저장하는 로직을 추가합니다.
        reviewRepository.create(review);
    }

    // 리뷰 수정
//    public boolean updateReview(Long reviewId, Review updatedReview) {
//        // 데이터베이스에서 reviewId에 해당하는 리뷰를 찾아 updatedReview로 업데이트하는 로직을 추가합니다.
//        // 업데이트에 성공하면 true, 실패하면 false를 반환합니다.
//        Review existingReview = reviewRepository.findOne(reviewId);
//
//        if (existingReview != null) {
//            // 업데이트할 정보를 새로운 정보로 설정
//            existingReview.setReviewContent(updatedReview.getReviewContent());
//            reviewRepository.create(existingReview);
//            return true;
//        } else {
//            // 예외 처리 또는 적절한 로직 추가
//            return false;
//        }
//    }

    // 리뷰 삭제
//    public boolean deleteReview(Long reviewId) {
//        // 데이터베이스에서 reviewId에 해당하는 리뷰를 삭제하는 로직을 추가합니다.
//        // 삭제에 성공하면 true, 실패하면 false를 반환합니다.
//        Review existingReview = reviewRepository.findOne(reviewId);
//
//        if (existingReview != null) {
//            reviewRepository.deleteById(reviewId);
//            return true;
//        } else {
//            // 예외 처리 또는 적절한 로직 추가
//            return false;
//        }
//
//
//
//        return false;
//    }

    // 모든 리뷰 조회
    public List<Review> getAllReviews() {
        // 데이터베이스에서 모든 리뷰를 조회하는 로직을 추가합니다.
        // 예시로 비어있는 리스트를 반환합니다.
        return reviewRepository.findAll();

    }
}
