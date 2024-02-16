package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.review.ReviewDto;
import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.service.ReviewCommentService;
import ArtBridge.ArtBridgelogin.service.ReviewService;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    // 리뷰 목록 조회
    @GetMapping
    public ResponseEntity<List<ReviewDto>> readAllReviews() {
        try {
            List<ReviewDto> reviews = reviewService.readAllReviews();
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    // 특정 리뷰 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> readReviewById(@PathVariable(value = "id") int id) {
        try {
            ReviewDto reviewDto = reviewService.readReviewBySeq(id);
            return ResponseEntity.ok(reviewDto);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }


    // 새로운 리뷰 생성
    @PostMapping("/new")
    public ResponseEntity<?> createReview(@RequestBody ReviewDto reviewDto) {
        try {
            ReviewDto createdReview = reviewService.createReview(reviewDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }


    // 리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable(value = "id") int id, @RequestBody ReviewDto updatedReview) {
        try {
            ReviewDto reviewDto = reviewService.updateReview(id, updatedReview);
            return ResponseEntity.ok(reviewDto);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable(value = "id") int id) {
        try {
            reviewService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }
}
