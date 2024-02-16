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

const ReviewList = [
  'https://image.chosun.com/sitedata/image/201702/20/2017022002398_0.jpg',
  'https://photo.akmall.com/image4/goods/38/18/43/19/138184319_01_350.jpg',
  'https://m.wart.co.kr/file_data/weart22/2022/08/01/b912e893198453807ef53d8c9b8125b4.jpg',
  'https://media.bunjang.co.kr/product/222196100_1_1682414243_w360.jpg',
  'https://contents.lotteon.com/itemimage/20231221131505/LO/20/85/85/14/86/_2/08/58/51/48/7/LO2085851486_2085851487_1.jpg/dims/resizef/720X720',
];

interface ReviewProp {
  itemSeq: number;
  seq:number
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
        <div className={styles.Cards} style={{ marginLeft: '22vh', display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))', gap: '20px', justifyContent: 'center' }}>
          {reviewsData.map((review) => (
            <ReviewCard currentUser={currentUser} key={review.seq} data={review} className={styles.ReviewCard} image={ReviewList[(review.seq) % 5]}/>
          ))}
        </div>
      )}</div>
    </Container>
  );
}

export default ReviewPage