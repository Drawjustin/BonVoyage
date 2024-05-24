'use client'
// Import necessary types from React and YouTube component
import React, { useState } from 'react';
import YouTube from 'react-youtube';
import Modal from 'react-modal';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import getCurrentUser from '@/app/actions/getCurrentUser';
import styles from './AuctionLivePage.module.scss';

// Define the props for the AuctionLivePage component
interface AuctionLivePageProps {}

// Define the AuctionLivePage component
const AuctionLivePage: React.FC<AuctionLivePageProps> = () => {
  const [modalOpen, setModalOpen] = useState(true);
  const currentUser = getCurrentUser();

  const navigate = useRouter();

  const customModalStyles: Modal.Styles = {
    overlay: {
      backgroundColor: " rgba(0, 0, 0, 0.5)",
      width: "100%",
      height: "100vh",
      zIndex: "10",
      position: "fixed",
      top: "0",
      left: "0",
    },
    content: {
      width: "600px",
      height: "400px",
      zIndex: "150",
      position: "absolute",
      top: "50%",
      left: "50%",
      transform: "translate(-50%, -50%)",
      borderRadius: "10px",
      boxShadow: "2px 2px 3px rgba(0, 0, 0, 0.2)",
      backgroundColor: "white",
      justifyContent: "center",
      overflow: "auto",
    },
  };

  const modalClose = () => {
    setTimeout(() => {
      setModalOpen(false);
    }, 2000);
  };

  return (
    <div className={styles.container}>
      {/* <Modal
        isOpen={modalOpen}
        onRequestClose={() => setModalOpen(false)}
        style={customModalStyles}
        ariaHideApp={false}
        contentLabel="Pop up Message"
        shouldCloseOnOverlayClick={false}
      >
      <div className={styles.guide_title}> 안내 영상 </div>
      <div className={styles.guide_video}>
      <YouTube
          videoId="vGhfVFr1OPk" //동영상 주소
          opts={{
            width: "100%",
            height: "330px",
            playerVars: {
            autoplay: 1, // 자동 재생 여부 
            modestbranding: 1, // 컨트롤 바에 유튜브 로고 표시 여부
            loop: 1, // 반복 재생
            playlist: "https://www.youtube.com/watch?v=vGhfVFr1OPk", //반복 재생으로 재생할 플레이 리스트
            },
          }}
          onReady={(e) => {
            e.target.mute(); // 소리 끔
          }}
          onEnd={() => {
            modalClose();
          }}
        />
      </div>
      </Modal> */}
      <div>
        <div className={styles.isLive}>
          <div className={styles.dot}></div>
          <p>00 작가 경매 Live</p>
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
                  playlist: "https://www.youtube.com/watch?v=vGhfVFr1OPk", //반복 재생으로 재생할 플레이 리스트
                },
              }}
              onReady={(e) => {
                e.target.mute(); // 소리 끔
              }}
            />
          </div>
          <div className={styles.live_input}>
            <div className={styles.live_chat}>
              <div className={styles.chat_title}>🗨️ Chat</div>
              <div className={styles.chat}>
                <button onClick={() => navigate.push("/AfterAuctionPage")}>경매 후</button>
                {/* <div>채팅1</div>
              <div>채팅2</div>
              <div>채팅3</div> */}
              </div>
              <input type="text" className={styles.chat_input} placeholder='채팅을 입력하세요'/>
            </div>
            <div className={styles.live_top}>
              <div className={styles.title}>현재 최고 호가</div>
              <div className={styles.top_price}>
                3,000
              </div>
              <div className={styles.history}>
                <div>[닉네임] 3,000 + 시간</div>
                <div>2,500</div>
                <div>2,000</div>
                <div>1,500</div>
                <div>1,000</div>
              </div>
            </div>
            <div className={styles.live_price}>
              <div className={styles.title}>금액 입찰</div>
              <input type="text" className={styles.chat_input} placeholder='보유 포인트 금액'/>
              <div className={styles.price_btn}>
                <button>5호가</button>
                <button>10호가</button>
                <button>15호가</button>
                <button>30호가</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AuctionLivePage;
