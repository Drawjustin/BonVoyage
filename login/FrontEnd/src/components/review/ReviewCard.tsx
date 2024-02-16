'use client'
import Image from 'next/image';
import { useRouter } from 'next/navigation';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import HeartButtonForReview from '../HeartButtonForReview/HeartButtonForReview';
import { fromNow } from '@/helpers/dayjs';
import styles from './ReviewCard.module.scss'


const ReviewCard = ({ data, currentUser, image }: any) => {
  
  const [ReviewDetail, setReviewDetail] = useState(null);
  const router = useRouter();
  console.log(data)

  useEffect(() => {
    const fetchReviewDetail = async () => {
      try {
        console.log(data.seq);
        const response = await axios.get(`https://i10a207.p.ssafy.io/api/review/${data.seq}`);
        setReviewDetail(response.data);

        localStorage.setItem('productDetail', JSON.stringify(response.data));
      } catch (error) {
        console.error('Error fetching product details:', error);
      }
    };

    fetchReviewDetail();
  }, [data.seq]);

  const handleCardClick = () => {
    // Add logic for handling card click
    router.push(`/ReviewDetailPage/${data.seq}`);
  };

  return (
    <div
    onClick={handleCardClick}
    className={styles.Card}>
        <div className={styles.card2}>
            <div className={styles.template}>
                <img
                    src={image}
                    sizes='auto'
                    className={styles.Image}
                    alt="Review"
                />
                <div className={styles.heartButton}>
                    <HeartButtonForReview 
                        reviewId={data.id}
                        currentUser={currentUser}
                    />
                </div>
            </div>
        <div className={styles.title}>
            {data.title}
        </div>
        <div>
                <div>
                    {fromNow(data.createdAt)}
                </div>
            </div>
        </div>
    </div>
  )
}

export default ReviewCard