import React from 'react';
import Heading from '../../Heading/Heading';
import Image from 'next/image';
import styles from './ProductHead.module.scss';

const AuctionHead = ({
  title,
  imageSrc,
  id,
  currentUser
}: any) => {
  return (
    <>
      <div className={styles.auction_head_container}>
        <Heading title={title} />
        <Image
          src={imageSrc}
          fill
          className={styles.image}
          alt="product"
        />
      </div>
    </>
  );
}

export default AuctionHead;
