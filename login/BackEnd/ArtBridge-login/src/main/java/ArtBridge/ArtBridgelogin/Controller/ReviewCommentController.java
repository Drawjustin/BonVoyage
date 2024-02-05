package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import ArtBridge.ArtBridgelogin.service.ReviewCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ReviewComment")
public class ReviewCommentController {
    // 리뷰 조회 API
    @Autowired
    private ReviewCommentService reviewCommentService;
    @GetMapping("/{id}")
    public List<ReviewComment> getAlLReviewComment(@PathVariable int seq) {
        return reviewCommentService.getAllReviewComment(seq);
    }

//    @GetMapping("/{id}")
//    public ReviewComment getReviewCommentById(@PathVariable Long seq) {
//        return reviewCommentService.findOne(seq);
//    }

    @PostMapping("/new")
    public ReviewComment createReviewComment(@RequestBody ReviewComment reviewComment) {
        return reviewCommentService.createReviewComment(reviewComment);
    }

    @PutMapping("/{id}")
    public ReviewComment updateReviewComment(@PathVariable Long seq, @RequestBody ReviewComment updatedReviewComment) {
        return reviewCommentService.updateReviewComment(seq, updatedReviewComment);
    }

    @DeleteMapping("/{id}")
    public void deleteReviewComment(@PathVariable Long seq) {
        reviewCommentService.deleteReviewComment(seq);
    }

}
