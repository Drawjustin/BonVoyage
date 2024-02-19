package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.review.ReviewCommentDto;
import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import ArtBridge.ArtBridgelogin.service.ReviewCommentService;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/review-comments")
public class ReviewCommentController {

    @Autowired
    private ReviewCommentService reviewCommentService;

    // 리뷰 댓글 목록 조회
    @GetMapping("/{reviewId}")
    public ResponseEntity<List<ReviewCommentDto>> readAllReviewComments(@PathVariable int reviewId) {
        try {
            List<ReviewComment> comments = reviewCommentService.readAllReviewComment(reviewId);
            return ResponseEntity.ok(convertToDtoList(comments));
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    // 새로운 리뷰 댓글 생성
    @PostMapping("/{memberId}/{reviewId}")
    public ResponseEntity<?> createReviewComment(@RequestBody ReviewCommentDto commentDto,
                                                 @PathVariable String memberId,
                                                 @PathVariable int reviewId) {
        try {
            reviewCommentService.createReviewComment(commentDto, memberId, reviewId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    // 리뷰 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReviewComment(@PathVariable Long id, @RequestBody ReviewCommentDto updatedCommentDto) {
        try {
            ReviewCommentDto updatedComment = reviewCommentService.updateReviewComment(id, updatedCommentDto);
            return ResponseEntity.ok(updatedComment);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    // 리뷰 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewComment(@PathVariable Long id) {
        try {
            reviewCommentService.deleteReviewComment(id);
            return ResponseEntity.noContent().build();
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    // ReviewComment 리스트를 ReviewCommentDto 리스트로 변환
    private List<ReviewCommentDto> convertToDtoList(List<ReviewComment> comments) {
        return comments.stream()
                .map(reviewCommentService::convertToDto)
                .toList();
    }
}
