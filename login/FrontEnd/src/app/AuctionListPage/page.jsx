'use client'
import React, { useEffect, useState } from "react";
import styles from './AuctionListPage.module.scss';
import Navbar from '../../components/Navbar/Navbar';
import FloatingButton from '@/components/FloatingButton/FloatingButton'
import axios from 'axios'
import getCurrentUser from '@/app/actions/getCurrentUser';
import AuctionCard from '@/components/Auctions/AuctionCard/AuctionCard'
import EmptyState from "@/components/EmptyState/EmptyState";
import m1 from './m1.jpg'
import m2 from './m2.jpg'
import { useRouter } from "next/navigation";

const AuctionListPage = async () => {

  const currentUser = getCurrentUser();
  const navigate = useRouter();

  const auctions = [{
    auctionSeq:1,
      imageSrc: m1,
      title: '별 헤는 밤',
  },
  {
    auctionSeq:2,
      imageSrc: m2,
      title: '샤라랄랄라랄라',
  }]

  async function handleFunc (seq, user) {

    console.log(seq, user);

    

    const Auction = await axios.get(`https://i10a207.p.ssafy.io/api/auction/{${seq}}`)
    const ClickedId = user.id;
    const WhetherArtist = user.role === 'artist' ? true : false;

    console.log(ClickedId, WhetherArtist);

    navigate.push(`/demos/dashboard/canvas-designer.html?open=${WhetherArtist}&sessionid=${Auction.auctionId}&userFullName=${ClickedId}`)
    
  }

  // const [auctions, setAuctions] = useState([]);

  // useEffect(() => {    
  //   const fetchData = async () => {
  //     try {
  //       const response = await axios.get('https://i10a207.p.ssafy.io/api/auction');

  //       setAuctions(response.data);
  //     } catch (error) {
  //       console.error('Error fetching products:', error);
  //       throw error; // Rethrow the error to be handled elsewhere
  //     }
  //   };
  
  //   fetchData();
  // }, [])

    return (
      <>
      <div className={styles.container}>
        <div className="container" style={{ marginTop: '10px', marginLeft: '12vh' , width: '85%', alignItems: 'center' }}>
          <h1 style={{ color: '#f1efee', textAlign: 'left', marginBottom: '10px' }}>경매 작품</h1>
            

            {
              (auctions.length === 0 || !auctions)
              ?
              <EmptyState showReset />
              :
              (
              <>
              <div className={styles.grid}>
              {auctions.map((auction) =>(
              <div onClick={() => handleFunc(auction.auctionSeq, currentUser)}>
                  <AuctionCard
                    currentUser={currentUser}
                    key={auction.auctionSeq}
                    data={auction}
                  />
                  </div>)
              )}
                  
              </div>
              </>)
            }
        </div>
        <FloatingButton href="/AuctionUploadPage">+</FloatingButton>
        </div>
    </>
  )
}

export default AuctionListPage