'use client'
import Image from 'next/image';
import { useRouter } from 'next/navigation';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import HeartButtonForReview from '../HeartButtonForReview/HeartButtonForReview';
import { fromNow } from '@/helpers/dayjs';
import styles from './ReviewCard.module.scss'


const ReviewList = [
  'https://image.chosun.com/sitedata/image/201702/20/2017022002398_0.jpg',
  'https://photo.akmall.com/image4/goods/38/18/43/19/138184319_01_350.jpg',
  'https://m.wart.co.kr/file_data/weart22/2022/08/01/b912e893198453807ef53d8c9b8125b4.jpg',
  'https://media.bunjang.co.kr/product/222196100_1_1682414243_w360.jpg',
  'https://contents.lotteon.com/itemimage/20231221131505/LO/20/85/85/14/86/_2/08/58/51/48/7/LO2085851486_2085851487_1.jpg/dims/resizef/720X720',
];

const ReviewCard = ({ data, currentUser, image }: any) => {
  
  const [ReviewDetail, setReviewDetail] = useState(null);
  const router = useRouter();
  console.log(data)

  useEffect(() => {
    const fetchReviewDetail = async () => {
      try {
        console.log(data.itemSeq);
        const response = await axios.get(`https://i10a207.p.ssafy.io/api/review/${data.itemSeq}`);
        setReviewDetail(response.data);

        localStorage.setItem('productDetail', JSON.stringify(response.data));
      } catch (error) {
        console.error('Error fetching product details:', error);
      }
    };

    fetchReviewDetail();
  }, [data.itemSeq]);

  const handleCardClick = () => {
    // Add logic for handling card click
    router.push(`/ReviewDetailPage/${data.itemSeq}`);
  };

  return (
    <div
    onClick={() => router.push(`/review/${data.itemSeq}`)}
    className={styles.Card}>
        <div className={styles.card2}>
            <div className={styles.template}>
                <img
                    src={ReviewList[(data.itemSeq)%4]}
                    sizes='auto'
                    className={styles.Image}
                    alt="Review"
                />
                <div className={styles.heartButton}>
                    <HeartButtonForReview 
                        reviewId={data.id}
                        currentUser={'11111'}
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