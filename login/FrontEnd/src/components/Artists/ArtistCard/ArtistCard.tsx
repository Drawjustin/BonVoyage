'use client'
import React from 'react';
import Image from 'next/image';
import HeartButton from '../../HeartButton/HeartButton';
import styles from './ArtistCard.module.scss';
import { useRouter } from 'next/navigation';

const ArtistCard = ({ data, currentUser, image }:any) => {

  const Navigate = useRouter();

  const handleCardClick = () => {
    Navigate.push(`/ArtistHomePage/${data.id}`)
  };

  return (
    <div
      onClick={handleCardClick}
      className={styles['artist-card']}
    >
      <div className={styles['image-container']}>
        <img
          src={image}
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
        {data.name}
      </div>

    </div>
  );
};

export default ArtistCard;