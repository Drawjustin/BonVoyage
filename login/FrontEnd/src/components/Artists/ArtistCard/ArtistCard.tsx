'use client'
import React from 'react';
import Image from 'next/image';
import HeartButton from '../../HeartButton/HeartButton';
import styles from './ArtistCard.module.scss';

const ArtistCard = ({ data, currentUser }:any) => {
  const handleCardClick = () => {
    // Add logic for handling card click
  };

  return (
    <div
      onClick={handleCardClick}
      className={styles['artist-card']}
    >
      <div className={styles['image-container']}>
        <Image
          src={data.imageSrc}
          fill
          sizes='auto'
          className={styles['product-image']}
          alt="artist"
        />
        <div className={styles['heart-button']}>
          <HeartButton 
            productId={data.id}
            currentUser={currentUser}
          />
        </div>
        <div className={styles.LikeNumber}>
          좋아요 수
        </div>
      </div>

      <div className={styles['artist-details']}>
        {data.title}
      </div>

    </div>
  );
};

export default ArtistCard;