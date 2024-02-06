package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.test.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Review")
public class ReviewController {
    // 리뷰 조회 API
    @Autowired
    private ReviewService reviewService;
    @GetMapping("/{reviewId}")
    public ResponseEntity<?> readReview(@PathVariable Integer reviewId) {
        return reviewService.readReviewById(reviewId);
    }
    @GetMapping
    public ResponseEntity<?> readAllReviews(@RequestParam(required = false) String sort) {
        List<Review> reviews;

        if (sort == null) {
            // 전체 아이템 조회 로직
            reviews = reviewService.readAllReviews();
        } else if (sort.equals("popular")) {
            // 인기 아이템 조회 로직
            reviews = reviewService.readAllReviews();
        } else if (sort.equals("new")) {
            // 최신 아이템 조회 로직
            reviews = reviewService.readAllReviews();
        } else {
            return new ResponseEntity<>("sort값이 잘못 들어왔습니다.", HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    // 리뷰 등록 API
    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody Review review) {
        // 실제로는 전달받은 review를 데이터베이스에 저장하는 로직이 들어갑니다.
        reviewService.createReview(review);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{reviewSeq}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Integer reviewSeq,
            @RequestBody Review updatedReview) {
        try {
            Review updated = reviewService.updateReview(reviewSeq, updatedReview);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            reviewService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
