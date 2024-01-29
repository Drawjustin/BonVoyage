import React, { useState } from 'react';
import styles from './ArtistHomePage.module.scss'
import Portfolio from '../../components/ArtistHomepage/Portfolio/Portfolio'
import Community from '../../components/ArtistHomepage/Community/Community'
import Calendar from '../../components/ArtistHomepage/Calendar/Calendar'
import Reviews from '../../components/ArtistHomepage/Reviews/Reviews'
import Profile from '../../components/ArtistHomepage/Profile/Profile'

const MAIN_DATA = [
  { id: 1, name: 'portfolio', text: '' },
  { id: 2, name: 'community', text: '' },
  { id: 3, name: 'calender', text: '' },
  { id: 4, name: 'reviews', text: '' },
];


const ArtistHomePage = () => {
  // 카테고리
  const selectComponent = {
    portfolio: <Portfolio />,
    community: <Community />,
    calender: <Calendar />,
    reviews: <Reviews />,
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
              <Profile />
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