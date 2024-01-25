import React from 'react';
import styles from '../../components/HomeComponent/HomeComponent.module.scss';
import Navbar from '@/components/Navbar/Navbar';
import PopularReviews from '@/components/HomeComponent/popularReviews/PopularReviews';
import AuctionComingSoon from '@/components/HomeComponent/auctionComingSoon/AuctionComingSoon';
import AuctionCal from '@/components/HomeComponent/auctionCal/AuctionCal';
import getCurrentUser from "../actions/getCurrentUser";
import getProducts, { ProductsParams } from "../actions/getProducts";

interface HomePageProps {
    searchParams: ProductsParams
  }

export default async function HomePage({searchParams}: HomePageProps) {

    const currentUser = await getCurrentUser();

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