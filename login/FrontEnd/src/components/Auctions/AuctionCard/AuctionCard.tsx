'use client'
import React, { useEffect, useState } from 'react';
import Image from 'next/image';
import styles from './AuctionCard.module.scss';
import { useRouter } from 'next/navigation';
import axios from 'axios';

const AuctionCard = ({ data, currentUser, image }:any) => {

    const navigate = useRouter();
    const isArtist = currentUser?.role === 'artist' ? true : false;
    const sessionId = data.auctionSessionId;
    const id = currentUser?.id.id;
    const aucseq = data.auctionSeq;
    const itemSeq = data.itemSeq;
    const [noticedItem, setNoticedItem] = useState<any>('');

    

    // console.log(data);

    const deleteAuction = async () => {
      try {
        await axios.delete(`https://i10a207.p.ssafy.io/api/auction/${data.auctionSeq}`);
      alert('삭제 성공');
      location.reload();
    } catch (error) {
          console.log(error);
          alert('삭제 실패');
        }
    }
  

    useEffect(() => {

      const noticeItem = async () => {
        try {
          const response = await axios.get(`https://i10a207.p.ssafy.io/api/item/${itemSeq}`);
          setNoticedItem(response.data);
      } catch (error) {
            console.log(error);
          }
      }

      noticeItem();
    }, [])

    

  return (
    <div
      className={styles['auction-card']}
    >
      <div className={styles['image-container']}>
        <img
          src={image}
          sizes='auto'
          className={styles['product-image']}
          alt="auction"
        />
      </div>

      <div className={styles['auction-details']}>
        {noticedItem.itemName}
      </div>

      <div className={styles.buttonContainer}>
        <div>
        {
            currentUser?.id
            ?
            <a href={`https://i10a207.p.ssafy.io:8080/demos/dashboard/canvas-designer.html?open=${isArtist}&sessionid=${sessionId}&userFullName=${id}&aucSeq=${aucseq}`} target='_blank'>경매 참여</a>
            :
            <></>
          }
        </div>
          
        

          {
            id === noticedItem?.artistId
            ?
            <button onClick={() => {navigate.push(`/AuctionModifyPage/${data.auctionSeq}`)}}>수정</button>
            :
            <></>
          }
        

        

          {
            id === noticedItem?.artistId
            ?
            <button onClick={deleteAuction}>삭제</button>
            :
            <></>
          }
        
          
          
      </div>

    </div>
  );
};

export default AuctionCard;