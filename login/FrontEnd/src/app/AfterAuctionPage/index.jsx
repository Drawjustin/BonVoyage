import React from 'react'
// import React from 'react'
import styles from './AfterAuctionPage.module.scss'
import YouTube from 'react-youtube';

const AfterAuctionPage = () => {
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
              videoId="DR7ynMK-5E8" //동영상 주소
              opts={{
                width: "100%",
                height: "500px",
                playerVars: {
                autoplay: 0, //자동 재생 여부 
                modestbranding: 1, //컨트롤 바에 유튜브 로고 표시 여부
                loop: 1, //반복 재생
                // playlist: "https://www.youtube.com/watch?v=vGhfVFr1OPk", //반복 재생으로 재생할 플레이 리스트
                },
              }}
              onReady={(e) => {
                e.target.mute(); // 소리 끔
              }}
            />
        </div>
      </div>
        <div className={styles.end_btn}>
          <button>종료</button>
        </div>
    </div>
  )
}

export default AfterAuctionPage;