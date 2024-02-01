package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Review")
public class ReviewController {
    // 리뷰 조회 API
    @Autowired
    private ReviewService reviewService;
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        // 실제로는 reviewId에 해당하는 리뷰를 데이터베이스에서 조회하는 로직이 들어갑니다.
        Review review = reviewService.getReviewById(reviewId);

        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 리뷰 등록 API
    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody Review review) {
        // 실제로는 전달받은 review를 데이터베이스에 저장하는 로직이 들어갑니다.
        reviewService.createReview(review);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 리뷰 수정 API
//    @PutMapping("/{reviewId}")
//    public ResponseEntity<Void> updateReview(@PathVariable Long reviewId, @RequestBody Review updatedReview) {
//        // 실제로는 reviewId에 해당하는 리뷰를 데이터베이스에서 찾아 업데이트하는 로직이 들어갑니다.
//        boolean updated = reviewService.updateReview(reviewId, updatedReview);
//
//        if (updated) {
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    // 리뷰 삭제 API
//    @DeleteMapping("/{reviewId}")
//    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
//        // 실제로는 reviewId에 해당하는 리뷰를 데이터베이스에서 삭제하는 로직이 들어갑니다.
//        boolean deleted = reviewService.deleteReview(reviewId);
//
//        if (deleted) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


}
