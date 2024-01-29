import React from 'react';
import Link from 'next/link';
import ArtistCard from '../../components/Signup/ArtistSignup/ArtistSignup'
import GuestCard from '../../components/Signup/MemberSignup/MemberSignup'
import styles from './SignupPage.module.scss';
import Navbar from '../../components/Navbar/Navbar';


const SignupPage = () => {
    return (
        <div>
            <Navbar />
            <div className={styles.title}>회원가입</div>
            <div className={styles.cards}>
                <div className={styles.card}>
                    <Link href="/SignupPage/SignupArtistPage"><ArtistCard /></Link>
                </div>
                <div className={styles.card}>
                    <Link href="/SignupPage/SignupMemberPage"><GuestCard /></Link>
                </div>
            </div>
        </div>
    )
}


export default SignupPage;