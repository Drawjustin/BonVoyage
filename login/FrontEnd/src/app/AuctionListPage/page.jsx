'use client'
import React from "react";
import styles from './AuctionListPage.module.scss';
import Navbar from '../../components/Navbar/Navbar';
import FloatingButton from '@/components/FloatingButton/FloatingButton'
import axios from 'axios'


const AuctionListPage = () => {

    const auctions = {} // await axios.get('https://i10a207.p.ssafy.io/api/item');
    // console.log(products.data);

    return (
        <div style={{ width: '1200px' }}>
        <Navbar />
            <div style={{ marginTop: '10vh' }}>
            <h1 style={{ color: '#f1efee'}}>경매 목록</h1>
            </div>

            {
              auctions.data?.length === 0
              ?
              <EmptyState showReset />
              :
              <>
              <div className={styles.grid}>
              {auctions.data?.map((auction) =>
                  <ProductCard
                    currentUser={'퍄퍄퍄'}
                    key={auction.itemSeq}
                    data={product}
                  />)}
              </div>
              </>
            }
        
            <FloatingButton
      href="/AuctionUploadPage">
        +
      </FloatingButton>

        </div>
    )
}

export default AuctionListPage