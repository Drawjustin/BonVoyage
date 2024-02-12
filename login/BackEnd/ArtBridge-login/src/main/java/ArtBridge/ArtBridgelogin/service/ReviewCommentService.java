package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.controller.dto.review.ReviewCommentDto;
import ArtBridge.ArtBridgelogin.controller.dto.review.ReviewDto;
import ArtBridge.ArtBridgelogin.domain.ReviewComment;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import ArtBridge.ArtBridgelogin.repository.ReviewCommentRepository;
import ArtBridge.ArtBridgelogin.repository.ReviewRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommentService {

    private final ReviewCommentRepository reviewCommentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // 리뷰 댓글 조회 API
    @Transactional
    public List<ReviewComment> readAllReviewComment(int reviewId) {
        List<ReviewComment> comments = reviewCommentRepository.readAllByReviewId((long) reviewId);
        if (comments.isEmpty()) {
            throw new NoDataFoundException("No comments found for reviewId: " + reviewId);
        }
        return comments;
    }

    // 리뷰 댓글 생성 API
    @Transactional
    public void createReviewComment(ReviewCommentDto commentForm, String memberId, int reviewSeq) {
        ReviewComment newComment = new ReviewComment();
        newComment.setReviewCommentContent(commentForm.getContent());
        newComment.setReviewCommentCreatedDate(LocalDateTime.now());
        newComment.setMember(memberRepository.readMemberById(memberId));
        newComment.setReview(reviewRepository.readBySeq(reviewSeq));


        reviewCommentRepository.create(newComment);
    }

    @Transactional
    public ReviewCommentDto updateReviewComment(Long id, ReviewCommentDto updatedCommentDto) {
        ReviewComment existingComment = reviewCommentRepository.readById(id);
        if (existingComment == null) {
            throw new NoDataFoundException("Comment not found with id: " + id);
        }

        existingComment.setReviewCommentContent(updatedCommentDto.getContent());

        // 엔티티를 DTO로 변환하여 반환
        return convertToDto(existingComment);
    }

    @Transactional
    public void deleteReviewComment(Long id) {
        ReviewComment existingComment = reviewCommentRepository.readById(id);
        if (existingComment == null) {
            throw new NoDataFoundException("Comment not found with id: " + id);
        }

        reviewCommentRepository.deleteById(id);
    }

    // ReviewComment를 ReviewCommentDto로 변환하는 메서드
    public ReviewCommentDto convertToDto(ReviewComment comment) {
        ReviewCommentDto commentDto = new ReviewCommentDto();
        commentDto.setContent(comment.getReviewCommentContent());
        // 다른 필드들도 필요에 따라 추가

        return commentDto;
    }

}
