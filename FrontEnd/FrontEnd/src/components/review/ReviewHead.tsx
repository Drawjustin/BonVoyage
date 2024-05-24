import React from 'react'
import Heading from '../Heading/Heading';
import Image from 'next/image';
// import HeartButtonForReview from '../HeartButtonForReview';
import style from './ReviewHead.module.scss'

// interface ReviewHeadProps {
//     title: string,
//     imageSrc: string;
//     id:string;
//     currentUser?: User | null;
// }

const ReviewHead = ({
    title,
    imageSrc,
    id,
    currentUser
}: any) => {
  return (
    <>
        <Heading
            title={title}
        />
        <div className={style.Head}>
            <Image
                src={imageSrc}
                fill
                objectFit='contain'
                className={style.Image}
                alt="Review"
            />
            <div className={style.heartButton}>
                {/* <HeartButtonForReview
                    reviewId={id}
                    currentUser={currentUser}
                /> */}
            </div>
        </div>
    </>
  )
}

export default ReviewHead