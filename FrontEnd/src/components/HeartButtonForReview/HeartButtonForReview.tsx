import React from 'react';
import styles from './HeartButtonForReview.module.scss';
// import useFavoriteForReview from '@/hooks/useFavoriteForReview';
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai';


interface HeartButtonForReviewProps {
  reviewId: string;
  currentUser?: string | null;
}

const HeartButtonForReview = ({
  productId,
  currentUser
}: any) => {

  const toggleFavorite = () => {console.log('클릭');}
  const hasFavorite = false;

  return (
    <div
      onClick={toggleFavorite}
      className={styles.heartButton}
    >
      <AiFillHeart
        size={24}
        className={hasFavorite ? styles.heartButton__filled : styles.heartButton__outline}
      />

      <AiOutlineHeart
        size={24}
        className={styles.heartButton__outline}
      />
    </div>
  );
};

export default HeartButtonForReview;