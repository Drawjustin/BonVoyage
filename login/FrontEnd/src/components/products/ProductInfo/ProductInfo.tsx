import React from 'react';
import Avatar from '../../Avatar';
import { formatTime } from '@/helpers/dayjs';
import styles from './ProductInfo.module.scss';

const ProductInfo = ({
  user,
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
      <div className={styles.description}>
        {description}
      </div>
    </div>
  );
}

export default ProductInfo;
