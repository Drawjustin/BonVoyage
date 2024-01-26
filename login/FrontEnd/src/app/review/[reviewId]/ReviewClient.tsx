'use client';
import Button from '@/components/button/Button';
import Container from '@/components/Container';
import ReviewHead from '@/components/review/ReviewHead';
import ReviewInfo from '@/components/review/ReviewInfo';
import { Review, User } from '@prisma/client';
import { useRouter } from 'next/navigation';
import React from 'react'
import styles from './ReviewClient.module.scss'

interface ReviewClientProps {
    Review: Review & {user: User};
    currentUser?: User | null;
}

const ReviewClient = ({Review, currentUser}: ReviewClientProps) => {
    const router = useRouter();
  return (
    <Container>
        <div
        className={styles.layout}>
            <div className={styles.Card}>
                <ReviewHead
                    title={Review.title}
                    imageSrc={Review.imageSrc}
                    id={Review.id}
                    currentUser={currentUser}
                />
                    <div
                        className={styles.Info}
                    >
                        <ReviewInfo
                            user={Review.user}
                            createdAt={Review.createdAt}
                            description={Review.description}
                        />
                    </div>
            </div>
            <div className={styles.button}>
                <Button
                    label='댓글달기'
                />

            </div>
        </div>


    </Container>
  )
}

export default ReviewClient