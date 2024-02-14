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

const AuctionListPage = async () => {

  const currentUser = '현재유저';
  let auctions = [];
  await axios.get(`https://i10a207.p.ssafy.io/api/auction`).then((response) => {auctions = response.data})
  .catch((error) => {console.log(error); auctions = []})

  // const auctions = [{
  //   auctionSeq:1,
  //     imageSrc: m1,
  //     title: '별 헤는 밤',
  // },
  // {
  //   auctionSeq:2,
  //     imageSrc: m2,
  //     title: '샤라랄랄라랄라',
  // }]

  async function handleFunc (seq, user) {

    const Auction = await axios.get(`https://i10a207.p.ssafy.io/api/auction/${seq}`)
    const ClickedId = user.artistId ? user.artistId : user.memberId
    const WhetherArtist = user.artistId ? true : false

    const send = {id:Auction.data, userId:ClickedId, IsArtist:WhetherArtist};

    const data = await axios.post('https://i10a207.p.ssafy.io/api/auction', send, { headers: 
    {
      'Content-Type': 'application/json;charset=UTF-8',
    }})
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
        <div className="container" style={{ marginTop: '10px', marginLeft: '23vh' , width: '85%', alignItems: 'center' }}>
          <h1 style={{ color: '#f1efee', textAlign: 'left', marginBottom: '10px' }}>경매 작품</h1>
            </div>

            {
              (auctions?.data?.length === 0 || !auctions?.data)
              ?
              <EmptyState showReset />
              :
              <>
              <div className={styles.grid}>
              {auctions.map((auction) =>
              <div onClick={() => handleFunc(auction.auctionSeq, currentUser)}>
                  <AuctionCard
                    currentUser={currentUser}
                    key={auction.auctionSeq}
                    data={auction}
                  />
                  </div>
                  )}
                  
              </div>
              </>
            }
 
    </>
  )
}

export default AuctionListPage