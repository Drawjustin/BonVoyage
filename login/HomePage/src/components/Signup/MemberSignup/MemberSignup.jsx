import React from 'react';
import styles from './MemberSignup.module.scss'

const MemberSignup = () => {
    return (
        <div className={styles.guest_card}>
            <div className={styles.guest_card_inner}>개인 회원</div>
        </div>
    )
}

export default MemberSignup;