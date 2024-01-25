import useFavoriteForReview from '@/hooks/useFavoriteForReview';
import { User } from '@prisma/client'
import React from 'react'
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai';

interface HeartButtonForReviewProps {
    reviewId: string;
    currentUser?: User | null;
}

const HeartButtonForReview = ({
    reviewId,
    currentUser
}: HeartButtonForReviewProps) => {
    const {
        hasFavorite,
        toggleFavorite
        } = useFavoriteForReview({
        reviewId,
        currentUser
    })
  return (
    <div
        onClick={toggleFavorite}
    className='relative transition cursor-pointer hover:opacity-80'>
        
        <AiFillHeart
            size={24}
            className={hasFavorite ? 'fill-rose-500 absolute -top-[2px] -right-[2px]' : 'fill-neutral-500 absolute -top-[2px] -right-[2px]'}
        />

        <AiOutlineHeart
            size={24}
            className='fill-white absolute -top-[2px] -right-[2px]'
        />

    </div>
  )
}

export default HeartButtonForReview