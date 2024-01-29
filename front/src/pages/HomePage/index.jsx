import React from 'react';
import styles from '../../components/HomeComponent/HomeComponent.module.scss';
import Navbar from '../../components/Navbar/Navbar';
import PopularReviews from '../../components/HomeComponent/PopularReviews';
import AuctionComingSoon from '../../components/HomeComponent/AuctionComingSoon';
import AuctionCal from '../../components/HomeComponent/AuctionCal/';
const HomePage = () => {

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

export default HomePage