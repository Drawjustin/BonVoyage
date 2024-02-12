package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.review.ReviewDto;
import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.repository.ReviewRepository;
import ArtBridge.ArtBridgelogin.service.ReviewService;
import ArtBridge.ArtBridgelogin.service.errorMessage.MyDataAccessException;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void createReview() {
        // Given
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setTitle("Test Title");
        reviewDto.setContent("Test Content");

        Review review = new Review();
        review.setReviewSeq(1);
        review.setReviewTitle("Test Title");
        review.setReviewContent("Test Content");

        // When
        reviewService.createReview(reviewDto);

        // Then
        Mockito.verify(reviewRepository, Mockito.times(1)).createReview(any(Review.class));
    }

    @Test
    void readAllReviews() {
        // Given
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review());
        reviews.add(new Review());

        when(reviewRepository.readAll()).thenReturn(reviews);

        // When
        List<ReviewDto> result = reviewService.readAllReviews();

        // Then
        assertEquals(reviews.size(), result.size());
    }

    @Test
    void readReviewBySeq() {
        // Given
        int reviewSeq = 1;
        Review review = new Review();
        review.setReviewSeq(reviewSeq);
        review.setReviewTitle("Test Title");
        review.setReviewContent("Test Content");

        when(reviewRepository.readBySeq(reviewSeq)).thenReturn(review);

        // When
        ReviewDto result = reviewService.readReviewBySeq(reviewSeq);

        // Then
        assertNotNull(result);
        assertEquals(review.getReviewContent(), result.getContent());
    }

    @Test
    void updateReview() {
        // Given
        int reviewSeq = 1;
        ReviewDto updatedReviewDto = new ReviewDto();
        updatedReviewDto.setTitle("Updated Title");
        updatedReviewDto.setContent("Updated Content");

        Review existingReview = new Review();
        existingReview.setReviewSeq(reviewSeq);
        existingReview.setReviewTitle("Test Title");
        existingReview.setReviewContent("Test Content");

        when(reviewRepository.readBySeq(reviewSeq)).thenReturn(existingReview);
        when(reviewRepository.updateReview(reviewSeq, existingReview)).thenReturn(existingReview);

        // When
        ReviewDto result = reviewService.updateReview(reviewSeq, updatedReviewDto);

        // Then
        assertNotNull(result);
        assertEquals(updatedReviewDto.getTitle(), result.getTitle());
        assertEquals(updatedReviewDto.getContent(), result.getContent());
    }

    @Test
    void updateReviewNotFound() {
        // Given
        int reviewSeq = 1;
        ReviewDto updatedReviewDto = new ReviewDto();
        updatedReviewDto.setTitle("Updated Title");
        updatedReviewDto.setContent("Updated Content");

        when(reviewRepository.readBySeq(reviewSeq)).thenReturn(null);

        // When/Then
        assertThrows(NoDataFoundException.class, () -> reviewService.updateReview(reviewSeq, updatedReviewDto));
    }

    @Test
    void deleteById() {
        // Given
        int reviewSeq = 1;

        // When
        reviewService.deleteById(reviewSeq);

        // Then
        Mockito.verify(reviewRepository, Mockito.times(1)).deleteById(reviewSeq);
    }

}