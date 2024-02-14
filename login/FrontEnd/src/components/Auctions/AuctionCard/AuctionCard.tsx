'use client'
import React from 'react';
import Image from 'next/image';
import styles from './AuctionCard.module.scss';

const AuctionCard = ({ data, currentUser }:any) => {

    const isArtist = currentUser?.role === 'artist' ? true : false;
    const sessionId = data.auctionSessionId;
    const id = currentUser?.id.id;
    const aucseq = data.auctionSeq;

  return (
    <div
      className={styles['auction-card']}
    >
      <div className={styles['image-container']}>
        <Image
          src={data.imageSrc}
          fill
          sizes='auto'
          className={styles['product-image']}
          alt="auction"
        />
      </div>

      <div className={styles['artist-details']}>
        {data.title}
      </div>

      <div>
          {
            currentUser?.id
            ?
            <a href={`https://i10a207.p.ssafy.io:8080/demos/dashboard/canvas-designer.html?open=${isArtist}&sessionid=${sessionId}&userFullName=${id}&aucSeq=${aucseq}`} target='_blank'>경매 참여</a>
            :
            <></>
          }
          
      </div>

    </div>
  );
};

export default AuctionCard;