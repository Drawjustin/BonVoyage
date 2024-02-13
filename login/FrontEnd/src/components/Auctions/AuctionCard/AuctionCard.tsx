'use client'
import React from 'react';
import Image from 'next/image';
import styles from './AuctionCard.module.scss';

const AuctionCard = ({ data, currentUser }:any) => {

  return (
    <div
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