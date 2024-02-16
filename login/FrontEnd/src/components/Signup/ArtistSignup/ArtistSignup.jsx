import React from 'react';
import styles from './ArtistSignup.module.scss'
// import icon from './img/artist.png'

const ArtistSignup = () => {
    return (
        <div className={styles.artist_card}>
            <div className={styles.artist_icon}>
                {/* <img src={icon} /> */}
            </div>
            <div className={styles.artist_card_inner}>작가</div>
        </div>
    )
}

export default ArtistSignup;