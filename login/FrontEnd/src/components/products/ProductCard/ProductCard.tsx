'use client'
import React from 'react';
import Image from 'next/image';
import HeartButton from '../../HeartButton/HeartButton';
import { fromNow } from '@/helpers/dayjs';
import styles from './ProductCard.module.scss';

const ProductCard = ({ data, currentUser }:any) => {
  const handleCardClick = () => {
    // Add logic for handling card click
  };

  return (
    <div
      onClick={handleCardClick}
      className={styles['product-card']}
    >
      <div className={styles['image-container']}>
        <Image
          src={data.imageSrc}
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
        {data.title}
      </div>
      <div className={styles['category']}>
        {data.category}
      </div>
      <div className={styles['price']}>
        <div>
          {data.price}{" "}
          <span>원</span>
        </div>
        <div className={styles['createdAt']}>
          {fromNow(data.createdAt)}
        </div>
      </div>
    </div>
  );
};

export default ProductCard;