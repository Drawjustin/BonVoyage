'use client'
import React, { useState } from 'react';
import styles from './ArtistHomePage.module.scss'
import dynamic from 'next/dynamic';
import Navbar from "@/components/Navbar/Navbar";

const ArtistPortfolio = dynamic(() => import('../../components/ArtistHomepage/ArtistPortfolio/ArtistPortfolio'), {ssr:false});
const ArtistTalk = dynamic(() => import('../../components/ArtistHomepage/ArtistTalk/ArtistTalk'), {ssr:false});
const ArtistAuctionCalendar = dynamic(() => import('../../components/ArtistHomepage/ArtistAuctionCalendar/ArtistAuctionCalendar'), {ssr:false});
const ArtistReview = dynamic(() => import('../../components/ArtistHomepage/ArtistReview/ArtistReview'), {ssr:false});
const ArtistProfile = dynamic(() => import('../../components/ArtistHomepage/ArtistProfile/ArtistProfile'), {ssr:false});

const MAIN_DATA = [
  { id: 1, name: 'portfolio', text: '정보' },
  { id: 2, name: 'talk', text: '소통' },
  { id: 3, name: 'calender', text: '일정' },
  { id: 4, name: 'review', text: '리뷰' },
];


const ArtistHomePage = () => {
  // 카테고리
  const selectComponent = {
    portfolio: <ArtistPortfolio />,
    talk: <ArtistTalk />,
    calender: <ArtistAuctionCalendar />,
    review: <ArtistReview />,
  }

  const [content, setContent] = useState(selectComponent.portfolio);
  const [btnActive, setBtnActive] = useState("portfolio");
  
  const handleClickButton = e => {
    const { name } = e.target;
    setContent(selectComponent[name]);
    // setBtnActive((prev) => {
    //   return e.target.value;
    // })
    setBtnActive(name);
  };

  return (
    <div>
        <Navbar />
      <div style={{ marginTop: '5vh' }} className={styles.container}>
        <div className={styles.contents}>
          <div className={styles.profile}>
              <ArtistProfile />
          </div>
          <div className={styles.content}>
            <div className={styles.inner_content}>
              <div className={styles.title}>
                <div className={styles.selectbtn}>
                  {MAIN_DATA.map((data) => (
                    <button onClick={handleClickButton} name={data.name} key={data.id}
                    className={btnActive === data.name ? styles.activebtn : ""}>
                      {data.text}
                    </button>
                  ))}
                </div>
              </div>
            <div className={styles.page_content}>
              <div className={styles.page_box}>
                {content}
              </div>
            </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ArtistHomePage;