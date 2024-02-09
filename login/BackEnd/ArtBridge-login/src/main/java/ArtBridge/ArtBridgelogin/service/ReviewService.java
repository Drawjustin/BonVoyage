package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.Controller.Dto.ReviewResisterForm;
import ArtBridge.ArtBridgelogin.domain.Review;
import ArtBridge.ArtBridgelogin.repository.ReviewRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.MyDataAccessException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    private EntityManager em;


    //Todo: CREATE
//    @Transactional
//    public void createReview(Review review) {
//        // 데이터베이스에 review를 저장하는 로직을 추가합니다.
//        reviewRepository.createReview(review);
//    }

    @Transactional
    public void createReview(ReviewResisterForm reviewResisterForm) throws DataAccessException {
        // 리뷰 데이터 유효성 검사를 추가할 수 있습니다.

        // ReviewResisterForm에서 Review 엔티티로 변환
        Review review = new Review();
        review.setReviewTitle(reviewResisterForm.getTitle());
        review.setReviewContent(reviewResisterForm.getContent());
        review.setReviewCreatedDate(LocalDateTime.now());
        review.setReviewVisit(0);
        // 다른 필요한 속성들을 설정합니다.

        // 리뷰를 데이터베이스에 저장
        try {
            reviewRepository.createReview(review);
        } catch (DataAccessException e) {
            // 데이터베이스 예외 처리: 원하는 방식으로 예외를 다룹니다.
            throw new MyDataAccessException("수정할 수 없습니다.", e);
        }
    }


    //Todo: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Review readReviewById(Integer reviewId) {

        // 실제로는 reviewId에 해당하는 리뷰를 데이터베이스에서 조회하는 로직이 들어갑니다.
        Review review = reviewRepository.readById(reviewId);

        try {
            return reviewRepository.readById(reviewId);

        } catch (DataAccessException e) {
            // 데이터베이스 예외 처리: 원하는 방식으로 예외를 다룹니다.
            throw new MyDataAccessException("읽을 수 없습니다.", e);
        }
    }


    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Review> readAllReviews() {
        return reviewRepository.readAll();
    }


    //Todo: UDATE
    @Transactional
    public Review updateReview(Integer reviewSeq, Review updatedReview) {
        return reviewRepository.updateReview(reviewSeq, updatedReview);
    }


    //Todo: DELETE
    @Transactional
    public void deleteById(Integer id) {
        reviewRepository.deleteById(id);
    }

}
