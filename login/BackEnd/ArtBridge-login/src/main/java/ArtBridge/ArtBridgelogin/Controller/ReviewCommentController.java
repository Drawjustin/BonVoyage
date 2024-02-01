package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import ArtBridge.ArtBridgelogin.service.ReviewCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ReviewComment")
public class ReviewCommentController {
    // 리뷰 조회 API
    @Autowired
    private ReviewCommentService reviewCommentService;
    @GetMapping("/{reviewCommentId}")
    public ResponseEntity<?> getReviewComment(@PathVariable Long reviewCommentId) {
        // 실제로는 reviewId에 해당하는 리뷰를 데이터베이스에서 조회하는 로직이 들어갑니다.
        ReviewComment reviewComment = reviewCommentService.getReviewById(reviewCommentId);

        if (reviewComment != null) {
            return new ResponseEntity<>(reviewComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 리뷰 등록 API
    @PostMapping
    public ResponseEntity<?> createReviewComment(@RequestBody ReviewComment review) {
        // 실제로는 전달받은 review를 데이터베이스에 저장하는 로직이 들어갑니다.
        reviewCommentService.createReview(review);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
