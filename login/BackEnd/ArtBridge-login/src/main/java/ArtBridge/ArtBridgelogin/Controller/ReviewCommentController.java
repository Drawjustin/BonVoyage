package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import ArtBridge.ArtBridgelogin.service.ReviewCommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ReviewComment> readAlLReviewComment(@PathVariable int seq) {
        return reviewCommentService.readAllReviewComment(seq);
    }

//    @readMapping("/{id}")
//    public ReviewComment readReviewCommentById(@PathVariable Long seq) {
//        return reviewCommentService.readOne(seq);
//    }

    @PostMapping("/new")
    public void createReviewComment(@RequestBody ReviewComment reviewComment) {
        reviewCommentService.createReviewComment(reviewComment);
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
