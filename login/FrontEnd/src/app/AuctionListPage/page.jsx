import React from "react";
import styles from './AuctionListPage.module.scss';
import Navbar from '../../components/Navbar/Navbar';
import FloatingButton from '@/components/FloatingButton/FloatingButton'
import axios from 'axios'
import getCurrentUser from '@/app/actions/getCurrentUser';
import AuctionCard from '@/components/Auctions/AuctionCard/AuctionCard'

const AuctionListPage = async () => {

    const currentUser = await getCurrentUser();

    const auctions = await axios.get('https://i10a207.p.ssafy.io/api/auction');

    return (
        <div style={{ width: '1200px' }}>
        <Navbar />
            <div style={{ marginTop: '10vh' }}>
            <h1 style={{ color: '#f1efee'}}>경매 목록</h1>
            </div>

            {
              (auctions.data?.length === 0 || !auctions.data)
              ?
              <EmptyState showReset />
              :
              <>
              <div className={styles.grid}>
              {auctions?.data?.map((auction) =>
                  <AuctionCard
                    currentUser={currentUser}
                    key={auction.auctionSeq}
                    data={auction}
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