import React from 'react';
import styles from './HeartButtonForReview.module.scss';
import useFavoriteForReview from '@/hooks/useFavoriteForReview';
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai';


interface HeartButtonForReviewProps {
  reviewId: string;
  currentUser?: string | null;
}

const HeartButtonForReview = ({
  reviewId,
  currentUser
}: HeartButtonForReviewProps) => {
  const { hasFavorite, toggleFavorite } = useFavoriteForReview({
    reviewId,
    currentUser
  });

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