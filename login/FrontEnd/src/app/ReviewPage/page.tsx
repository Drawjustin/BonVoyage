'use client'
import React, { useState, useEffect } from "react";
import Image from "next/image";
import axios from "axios";
// import getReviews, { ReviewsParams } from "../actions/getReviews";
import Container from "@/components/Container/Container";
import EmptyState from "@/components/EmptyState/EmptyState";
// import getCurrentUser from "../actions/getCurrentUser";
// import FloatingButton from "@/components/FloatingButton";
import ReviewCard from "@/components/review/ReviewCard";
// import Pagination from "@/components/Pagination";
import Carousel from '@/components/carousel/Carousel';
import styles from './review.module.scss';
import PurchaseInfo from "@/components/MyPageComponent/PurchaseInfo/PurchaseInfo";
import getCurrentUser from '@/app/actions/getCurrentUser';

// interface ReviewPageProps {
//   searchParams: ReviewsParams
// }

const CAROUSEL_IMAGES = [
  'https://villiv.co.kr/data/2022/06/2022-06-27_16-25-25-23218-1656314725.jpg',
  'https://img.maisonkorea.com/2022/05/msk_6270961acd116.jpg',
  'https://www.wart.or.kr/main_banner/new-ex-23-04-mo.jpg',
]

interface ReviewProp {
  reviewSeq: number;
  // Add other properties based on your actual data structure
}


const ReviewPage = () => {
  const [reviewsData, setReviewsData] = useState<ReviewProp[]>([]); // State to store reviews data

  const currentUser = getCurrentUser();

  useEffect(() => {
    // Fetch reviews data from the API
    const fetchReviews = async () => {
      try {
        const response = await axios.get<ReviewProp[]>('https://i10a207.p.ssafy.io/api/review');
        setReviewsData(response.data);
      } catch (error) {
        console.error("Error fetching reviews:", error);
      }
    };

    fetchReviews();
  }, []);

  return (
    <Container>
      <div>
      <h2 style={{ marginTop: '10px', color: '#f1efee'}}>리뷰</h2>
      <Carousel carouselList={CAROUSEL_IMAGES}/>
      {/* <PurchaseInfo reviews={reviewsData} /> */}
      {reviewsData.length === 0 ? (
        <EmptyState showReset />
      ) : (
        <div className={styles.Cards}>
          {reviewsData.map((review) => (
            <ReviewCard currentUser={currentUser} key={review.reviewSeq} data={review} />
          ))}
        </div>
      )}</div>
    </Container>
  );
}

export default ReviewPage