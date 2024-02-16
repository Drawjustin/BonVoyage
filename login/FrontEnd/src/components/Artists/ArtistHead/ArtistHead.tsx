import React from 'react';
import Heading from '../../Heading/Heading';
import Image from 'next/image';
import HeartButton from '../../HeartButton/HeartButton';
import styles from './ProductHead.module.scss';

const ArtistHead = ({
  title,
  imageSrc,
  id,
  currentUser
}: any) => {
  return (
    <>
      <div className={styles.artist_head_container}>
        <Heading title={title} />
        <Image
          src={imageSrc}
          fill
          className={styles.image}
          alt="product"
        />
        <div className={styles.heart_button}>
          <HeartButton productId={id} currentUser={currentUser} />
        </div>
      </div>
    </>
  );
}

export default ArtistHead;
