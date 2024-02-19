'use client'
// Import necessary types from React and YouTube component
import React from 'react';
import YouTube from 'react-youtube';
import { useRouter } from 'next/navigation';
import styles from './AfterAuctionPage.module.scss';


// Define the AfterAuctionPage component
const AfterAuctionPage = () => {
  const navigate = useRouter();

  const videoOpts = {
    width: "100%",
    height: "500px",
    playerVars: {
      autoplay: 0,
      modestbranding: 1,
      loop: 1,
      playlist: "https://www.youtube.com/watch?v=vGhfVFr1OPk",
    },
  };

  return (
    <div className={styles.container}>
      <div className={styles.isLive}>
        <div className={styles.title}>
          <div className={styles.dot}></div>
          <p>00 작가와의 만남</p>
        </div>
        <button>계약서</button>
      </div>
      <div className={styles.live_video}>
        <div className={styles.live}>
          <YouTube
            videoId="DR7ynMK-5E8"
            opts={videoOpts}
            onReady={(e) => {
              e.target.mute();
            }}
          />
        </div>
      </div>
      <div className={styles.end_btn}>
        <button onClick={() => navigate.push('/')}>종료</button>
      </div>
    </div>
  );
};

export default AfterAuctionPage;
