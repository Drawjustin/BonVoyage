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


  const dummyData = [
    {
      id: 1,
      title: "별이 빛나는 밤",
      image: "./m1.jpg",
      price: 19000,
    },
    {
      id: 2,
      title: "배",
      image: "./m2.jpg",
      price: 240000,
    },
    {
      id: 3,
      title: "해바라기",
      image: "./m3.jpg",
      price: 650000,
    },
    {
      id: 4,
      title: "숲",
      image: "./m4.jpg",
      price: 14000,
    },
  ];


  useEffect(() => {    
    const fetchData = async () => {
      try {
        const response = axios.get('https://i10a207.p.ssafy.io/api/auction');

        setAuctions(response.data);
      } catch (error) {
        console.error('Error fetching products:', error);
        throw error; // Rethrow the error to be handled elsewhere
      }
    };
  
    fetchData();
  }, [])

    return (
      <>
        <div className="container" style={{ marginTop: '10px', marginLeft: '23vh' , width: '85%', alignItems: 'center' }}>
          <h1 style={{ color: '#f1efee', textAlign: 'left', marginBottom: '10px' }}>경매 작품</h1>
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
 
    </>
  )
}

export default AuctionListPage