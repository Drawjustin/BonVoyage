import React from 'react'
import styles from './CardSkeleton.module.scss'
import Skeleton from 'react-loading-skeleton'
import "react-loading-skeleton"

const CardSkeleton = () => {
  return (
    <div className={styles.card_skeleton_container}>
        <div className={styles.card_skeleton}>
            <Skeleton height={350} />
        </div>
        <div className={styles.card_skeleton}>
            <Skeleton height={350} />
        </div>
        <div className={styles.card_skeleton}>
            <Skeleton height={350} />
        </div>
        <div className={styles.card_skeleton}>
            <Skeleton height={350} />
        </div>
    </div>
)
}

export default CardSkeleton