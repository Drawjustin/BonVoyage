import React from 'react';
import Avatar from '../../Avatar';
import styles from './ProductInfo.module.scss';

const AuctionInfo = ({
  auction,
  description
}: any) => {
  return (
    <div className={styles.auction_info_container}>
      <div className={styles.user_info}>
          <Avatar src={auction?.image} />
          <p>{auction?.name}</p>
        </div>
      <div className={styles.description}>
        {description}
      </div>
    </div>
  );
}

export default AuctionInfo;
