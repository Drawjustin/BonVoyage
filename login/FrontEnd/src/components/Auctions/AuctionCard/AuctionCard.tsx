import React from 'react';
import Image from 'next/image';
import HeartButton from '../../HeartButton/HeartButton';
import styles from './ProductCard.module.scss';

const AuctionCard = ({ data, currentUser }:any) => {
  const handleCardClick = () => {
    // Add logic for handling card click
  };

  return (
    <div
      onClick={handleCardClick}
      className={styles['auction-card']}
    >
      <div className={styles['image-container']}>
        <Image
          src={data.imageSrc}
          fill
          sizes='auto'
          className={styles['product-image']}
          alt="artist"
        />
      </div>

      <div className={styles['artist-details']}>
        {data.title}
      </div>

    </div>
  );
};

export default AuctionCard;