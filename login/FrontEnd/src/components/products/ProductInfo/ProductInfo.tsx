import React from 'react';
import Avatar from '../../Avatar';
import ProductCategory from '../ProductCategory/ProductCategory';
import { formatTime } from '@/helpers/dayjs';
import styles from './ProductInfo.module.scss';

const ProductInfo = ({
  user,
  category,
  createdAt,
  description
}: any) => {
  return (
    <div className={styles.product_info_container}>
      <div>
        <div className={styles.user_info}>
          <Avatar src={user?.image} />
          <p>{user?.name}</p>
        </div>
        <div className={styles.time}>
          {formatTime(createdAt)}
        </div>
      </div>
      <hr />
      {category &&
        <ProductCategory
          icon={category.icon}
          label={category.label}
          description={category.description}
        />
      }
      <hr />
      <div className={styles.description}>
        {description}
      </div>
    </div>
  );
}

export default ProductInfo;
