import React from 'react';
import styles from './GuestSignup.module.scss'

const GuestSignup = () => {
    return (
        <div className={styles.guest_card}>
            <div className={styles.guest_card_inner}>개인 회원</div>
        </div>
    )
}

export default GuestSignup;