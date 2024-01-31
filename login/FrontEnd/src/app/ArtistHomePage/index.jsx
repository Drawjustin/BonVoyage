import React, { useState } from 'react';
import styles from './ArtistHomePage.module.scss'
import ArtistPortfolio from '../../components/ArtistHomepage/ArtistPortfolio/ArtistPortfolio'
import ArtistTalk from '../../components/ArtistHomepage/ArtistTalk/ArtistTalk'
import ArtistAuctionCalendar from '../../components/ArtistHomepage/ArtistAuctionCalendar/ArtistAuctionCalendar'
import ArtistReview from '../../components/ArtistHomepage/ArtistReview/ArtistReview'
import ArtistProfile from '../../components/ArtistHomepage/ArtistProfile/ArtistProfile'

const MAIN_DATA = [
  { id: 1, name: 'portfolio', text: '' },
  { id: 2, name: 'talk', text: '' },
  { id: 3, name: 'calender', text: '' },
  { id: 4, name: 'review', text: '' },
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
      <div className={styles.container}>
        <div className={styles.contents}>
          <div className={styles.profile}>
              <ArtistProfile />
          </div>
          <div className={styles.content}>
            <div className={styles.inner_content}>
              <div className={styles.title}>
                <div className={styles.pagetitle}>안녕하세용 ⸜( ˙ ˘ ˙)⸝♡</div>
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
  )
}

export default ArtistHomePage;