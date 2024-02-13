'use client'
import React, { useState, useEffect } from 'react';
import Image from 'next/image';
import HeartButton from '../../HeartButton/HeartButton';
import { fromNow } from '@/helpers/dayjs';
import styles from './ProductCard.module.scss';
import Link from 'next/link';
import axios from 'axios';
import { useRouter } from 'next/navigation';


// get 요청

const ProductCard = ({ data, currentUser }:any) => {

  const [ProductDetail, setProductDetail] = useState(null);
  const router = useRouter();

  useEffect(() => {
    const fetchProductDetail = async () => {
      try {
        console.log(data.itemSeq);
        const response = await axios.get(`https://i10a207.p.ssafy.io/api/item/{${data.itemSeq}}`);
        setProductDetail(response.data);
      } catch (error) {
        console.error('Error fetching product details:', error);
      }
    };

    fetchProductDetail();
  }, [data.itemSeq]);

  const handleCardClick = () => {
    // Add logic for handling card click
    const router = useRouter()

    router.push(`/${data.itemSeq}/ProductDetailPage`);
  };

  return (
    <div
      onClick={handleCardClick}
      className={styles['product-card']}
    >
      <div className={styles['image-container']}>
        <Image
          src={data.image}
          fill
          sizes='auto'
          className={styles['product-image']}
          alt="product"
        />
        <div className={styles['heart-button']}>
          <HeartButton 
            productId={data.id}
            currentUser={currentUser}
          />
        </div>
      </div>
      <div className={styles['product-details']}>
        {data.itemName}
      </div>
      <div className={styles['price']}>
        <div>
          {data.itemSellPrice}{" "}
          <span>원</span>
        </div>
        <div className={styles['createdAt']}>
          {fromNow(data.itemCreatedDate)}
        </div>
      </div>
    </div>
  );
};

export default ProductCard;