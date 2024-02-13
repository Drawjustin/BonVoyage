import React from 'react';
import Link from 'next/link';
import ArtistCard from '../../components/Signup/ArtistSignup/ArtistSignup'
import GuestCard from '../../components/Signup/MemberSignup/MemberSignup'
import styles from './SignupPage.module.scss';
import getCurrentUser from '@/app/actions/getCurrentUser';

const SignupPage = async () => {
    const currentUser = await getCurrentUser();
    return (
        <div className={styles.container}>
            <div className={styles.title} style={{ color: '#f1efee' }}>회원가입</div>
            <div className={styles.cards}>
                <div className={styles.card}>
                    <Link href="/SignupPage/SignupArtistPage" className={styles.linkstyle}><ArtistCard /></Link>
                </div>
                <div className={styles.card}>
                    <Link href="/SignupPage/SignupMemberPage" className={styles.linkstyle}><GuestCard /></Link>
                </div>
            </div>
        </div>
    )
}


export default SignupPage;