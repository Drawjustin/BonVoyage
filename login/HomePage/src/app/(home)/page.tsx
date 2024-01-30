import React from 'react';
import styles from '../../components/HomeComponent/HomeComponent.module.scss';
import Navbar from '@/components/Navbar/Navbar';
import PopularReviews from '@/components/HomeComponent/popularReviews/PopularReviews';
import AuctionComingSoon from '@/components/HomeComponent/auctionComingSoon/AuctionComingSoon';
import AuctionCal from '@/components/HomeComponent/auctionCal/AuctionCal';


export default async function HomePage() {

    return (
        <div className={styles.home}>
            <Navbar />
            <div className={styles.main_content}>
                <PopularReviews />
                <AuctionComingSoon />
                <AuctionCal />

            </div>
        </div>
        
    );
};