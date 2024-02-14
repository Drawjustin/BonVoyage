package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.review.ReviewDto;
import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.OrderDetail;
import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.repository.*;
import ArtBridge.ArtBridgelogin.service.errorMessage.MyDataAccessException;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // CREATE
    @Transactional
    public ReviewDto createReview(ReviewDto reviewDto) {
        try {
            Review review = convertToEntity(reviewDto);
            review.setReviewCreatedDate(LocalDateTime.now());
            review.setReviewVisit(0);
            review.setReviewContent(reviewDto.getContent());
            review.setReviewVisit(0);
            review.setReviewCreatedDate(LocalDateTime.now());
            review.setReviewTitle(reviewDto.getTitle());

            review.setMember(memberRepository.readMemberById(reviewDto.getMemberId()));
            //review.setItem(itemRepository.readBySeq(reviewDto.getItemSeq()));
            review.setArtist(artistRepository.readArtistById(reviewDto.getArtistId()));
            review.setItem(0);
            Review newReview = reviewRepository.createReview(review);
            System.out.println(newReview.getReviewContent());
            reviewDto.setSeq(newReview.getReviewSeq());

            System.out.println(reviewDto.toString());


            return reviewDto;
        } catch (DataAccessException e) {
            throw new MyDataAccessException("수정할 수 없습니다.", e);
        }
    }

    // READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<ReviewDto> readAllReviews() {
        try {
            List<Review> reviews = reviewRepository.readAll();

            if (reviews.isEmpty()) {
                return Collections.emptyList();
            }

            return reviews.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Failed to read all reviews", e);
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ReviewDto readReviewBySeq(Integer reviewSeq) {
        try {
            Review review = reviewRepository.readBySeq(reviewSeq);
            return convertToDto(review);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("읽을 수 없습니다.", e);
        }
    }

    // UPDATE
    @Transactional
    public ReviewDto updateReview(Integer reviewSeq, ReviewDto updatedReviewDto) {
        try {
            Review existingReview = reviewRepository.readBySeq(reviewSeq);
            if (existingReview == null) {
                throw new NoDataFoundException("리뷰가 존재하지 않습니다.");
            }

            BeanUtils.copyProperties(updatedReviewDto, existingReview);
            // 필요한 경우 다른 필드들도 복사

            reviewRepository.updateReview(reviewSeq, existingReview);
            return convertToDto(existingReview);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Failed to update review", e);
        }
    }

    // DELETE
    @Transactional
    public void deleteById(Integer id) {
        try {
            reviewRepository.deleteById(id);
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to delete review with ID: " + id, e);
        }
    }

    private ReviewDto convertToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setSeq(review.getReviewSeq());
        reviewDto.setContent(review.getReviewContent());
        reviewDto.setTitle(reviewDto.getTitle());

        return reviewDto;
    }

    private Review convertToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewDto, review);
        return review;
    }
}
