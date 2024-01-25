'use client';
import Button from '@/components/Button';
import Container from '@/components/Container';
import ReviewHead from '@/components/review/ReviewHead';
import ReviewInfo from '@/components/review/ReviewInfo';
import { Review, User } from '@prisma/client';
import { useRouter } from 'next/navigation';
import React from 'react'

interface ReviewClientProps {
    Review: Review & {user: User};
    currentUser?: User | null;
}

const ReviewClient = ({Review, currentUser}: ReviewClientProps) => {
    const router = useRouter();
  return (
    <Container>
        <div
        className='max-w-screen-lg mx-auto'>
            <div className='flex flex-col gap-6'>
                <ReviewHead
                    title={Review.title}
                    imageSrc={Review.imageSrc}
                    id={Review.id}
                    currentUser={currentUser}
                />
                    <div
                        className='grid grid-cols-1 mt-6 md:grid-colss-2 md:gap-10'
                    >
                        <ReviewInfo
                            user={Review.user}
                            createdAt={Review.createdAt}
                            description={Review.description}
                        />
                    </div>
            </div>
            <div className='mt-10'>
                <Button
                    label='댓글달기'
                />

            </div>
        </div>


    </Container>
  )
}

export default ReviewClient