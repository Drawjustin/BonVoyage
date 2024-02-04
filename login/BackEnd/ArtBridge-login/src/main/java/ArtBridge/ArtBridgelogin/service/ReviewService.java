package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.repository.ReviewRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    private EntityManager em;

    @Transactional
    public ResponseEntity<?> getReviewById(Integer reviewId) {

        // 실제로는 reviewId에 해당하는 리뷰를 데이터베이스에서 조회하는 로직이 들어갑니다.
        Review review = em.find(Review.class, reviewId);

        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public void createReview(Review review) {
        // 데이터베이스에 review를 저장하는 로직을 추가합니다.
        reviewRepository.create(review);
    }

    @Transactional
    public Review updateReview(Integer reviewSeq, Review updatedReview) {
        return reviewRepository.updateReview(reviewSeq, updatedReview);
    }

    @Transactional
    public void deleteById(Integer id) {
        reviewRepository.deleteById(id);
    }

    @Transactional
    public List<Review> getAllReviews() {
        // 데이터베이스에서 모든 리뷰를 조회하는 로직을 추가합니다.
        // 예시로 비어있는 리스트를 반환합니다.
        return reviewRepository.findAll();

    }
}
