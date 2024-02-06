import React from 'react';
import Avatar from '../../Avatar';
import styles from './ProductInfo.module.scss';

const ArtistInfo = ({
  artist,
  description
}: any) => {
  return (
    <div className={styles.artist_info_container}>
      <div className={styles.user_info}>
          <Avatar src={artist?.image} />
          <p>{artist?.name}</p>
        </div>
      <div className={styles.description}>
        {description}
      </div>
    </div>
  );
}

export default ArtistInfo;
