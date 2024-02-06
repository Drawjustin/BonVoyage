package ArtBridge.ArtBridgelogin.test;

import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.repository.ReviewRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    private EntityManager em;


    //Todo: CREATE
    @Transactional
    public void createReview(Review review) {
        // 데이터베이스에 review를 저장하는 로직을 추가합니다.
        reviewRepository.createReview(review);
    }


    //Todo: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ResponseEntity<?> readReviewById(Integer reviewId) {

        // 실제로는 reviewId에 해당하는 리뷰를 데이터베이스에서 조회하는 로직이 들어갑니다.
        Review review = reviewRepository.readById(reviewId);

        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Review> readAllReviews() {
        return reviewRepository.readAll();
    }


    //Todo: UDATE
    @Transactional
    public Review updateReview(Integer reviewSeq, Review updatedReview) {
        return reviewRepository.updateReview(reviewSeq, updatedReview);
    }


    //Todo: DELETE
    @Transactional
    public void deleteById(Integer id) {
        reviewRepository.deleteById(id);
    }

}
