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
import AuctionPagination from './AuctionPagination'

const AuctionListPage = async () => {


  const navigate = useRouter();
  const currentUser = getCurrentUser();
  let auctions = [];
  await axios.get(`https://i10a207.p.ssafy.io/api/auction`).then((response) => {auctions = response.data})
  .catch((error) => {console.log(error); auctions = []})

  // auctions = [{
  //   auctionSeq:1,
  //     imageSrc: m1,
  //     title: '별 헤는 밤',
  // },
  // {
  //   auctionSeq:2,
  //     imageSrc: m2,
  //     title: '샤라랄랄라랄라',
  // }]

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
      
        <div className="container" style={{ marginTop: '10px', marginLeft: '30vh' , width: '85%', alignItems: 'center' }}>
        <h1 style={{ color: '#f1efee', textAlign: 'left', marginBottom: '10vh' }}>경매 작품</h1>
            <AuctionPagination/>
            </div>
            {
          (currentUser !== null && currentUser.role === 'artist') ?
          <FloatingButton href="/AuctionUploadPage">+</FloatingButton>
          :
          <></>
        }
        </div>
    </>
  )
}

export default AuctionListPage