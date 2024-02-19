import React from 'react'
import { IconType } from 'react-icons'
import styles from './ReviewCategory.module.scss';

interface ReviewCategoryProps {
    icon: IconType;
    label: string;
    description: string;
}

const ReviewCategory = ({
    icon: Icon,
    label,
    description
}: ReviewCategoryProps) => {
    return (
        <div className={styles.myComponent}>
          <div className={`${styles.myComponent__icon} text-neutral-600`}>
            <Icon></Icon>
          </div>
          <div className={styles.myComponent__content}>
            <div className={styles.myComponent__content__label}>
              {label}
            </div>
            <div className={`${styles.myComponent__content__description} text-neutral-500`}>
              {description}
            </div>
          </div>
        </div>
      );
}

export default ReviewCategory