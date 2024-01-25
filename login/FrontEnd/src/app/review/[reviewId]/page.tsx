import getCurrentUser from '@/app/actions/getCurrentUser';
import getReviewById from '@/app/actions/getReviewById'
import EmptyState from '@/components/EmptyState';
import React from 'react'
import ReviewClient from './ReviewClient';


interface Params {
  reviewId?: string
}

const ReviewPage = async ({params}: { params: Params}) => {
  
  const review = await getReviewById(params);
  const currentUser = await getCurrentUser();

  if(!review) {
    return (
      <EmptyState />
    )
  }

  return (
    
    <ReviewClient
      Review={review}
      currentUser={currentUser}
    />

  )
}

export default ReviewPage