'use client'
import { Review, User } from '@prisma/client'
import Image from 'next/image';
import { useRouter } from 'next/navigation';
import React from 'react'
import HeartButtonForReview from '../HeartButtonForReview';
import { fromNow } from '@/helpers/dayjs';
import styles from './ReviewCard.module.scss'

interface ReviewCardProps {
    data: Review;
    currentUser?: User | null;
}

const ReviewCard = ({ data, currentUser }: ReviewCardProps) => {

    const router = useRouter();
  return (
    <div
    onClick={() => router.push(`/review/${data.id}`)}
    className={styles.Card}>
        <div className={styles.card2}>
            <div className={styles.template}>
                <Image
                    src={data.imageSrc}
                    fill
                    sizes='auto'
                    className={styles.Image}
                    alt="Review"
                />
                <div className={styles.heartButton}>
                    <HeartButtonForReview 
                        reviewId={data.id}
                        currentUser={currentUser}
                    />
                </div>
            </div>
        <div className={styles.title}>
            {data.title}
        </div>
        <div>
                <div>
                    {fromNow(data.createdAt)}
                </div>
            </div>
        </div>
    </div>
  )
}

export default ReviewCard