'use client'
import React, { useEffect, useState } from "react";
import styles from './AuctionListPage.module.scss';
import Navbar from '../../components/Navbar/Navbar';
import FloatingButton from '@/components/FloatingButton/FloatingButton'
import axios from 'axios'
import getCurrentUser from '@/app/actions/getCurrentUser';
import AuctionCard from '@/components/Auctions/AuctionCard/AuctionCard'
import EmptyState from "@/components/EmptyState/EmptyState";

const AuctionListPage = async () => {

  const currentUser = getCurrentUser();
  const [auctions, setAuctions] = useState([]);

  useEffect(() => {    
    const fetchData = async () => {
      try {
        const response = await axios.get('https://i10a207.p.ssafy.io/api/auction');

        setAuctions(response.data);
      } catch (error) {
        console.error('Error fetching products:', error);
        throw error; // Rethrow the error to be handled elsewhere
      }
    };
  
    fetchData();
  }, [])

    return (
        <div style={{ width: '1200px' }}>
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