import React from 'react';
import styles from '../HomeComponent.module.scss';

const AuctionComingSoon = () => {
  
  const artworks = [
    { id: 1, title: '모나리자', amount: '10억 원', description: '눈썹이 없는 것이 매우 매력적인 작품', specs: '50x70cm', artist: '레오나르도 다빈치', otherarts: '최후의 만찬'},
    { id: 2, title: '해바라기', amount: '30000000 원', description: '저희 어머니가 꽃을 좋아해서요..', specs: '30x60cm', artist: '반 고흐', otherarts: '자화상'},
    { id: 3, title: '절규', amount: '1억 50000000 원', description: '요즘 내 상황을 그려봤어요.', specs: '500x700cm', artist: '뭉크',  otherarts: '뭉키'},
    
  ];

  // const [selectedArtwork, setSelectedArtwork] = useState(null);

  // const handleArtworkClick = (artwork) => {
  //   setSelectedArtwork(artwork);
  // };

  return (
    <div className={styles.auction_container}>
        <div className={styles.auction_center_container}>
          <div className={styles.auction_details}>
            <div>
              <div className={styles.box1}>  
                <div className={styles.in_box1}>
                <div className={styles.box1_wrap}>
                  <svg value='60' className={styles.svg1} open>
                    <defs>
                      <linearGradient id='gradient' x1='0%' y1='0%' x2='0%' y2='100%'>
                        <stop offset='0%' stopColor='#FFFEC4'></stop>
                        <stop offset='50%' stopColor='#DFFFD8'></stop>
                        <stop offset='90%' stopColor='#95DAC1'></stop>
                      </linearGradient>
                    </defs>                  

                    <circle className={styles.frame} cx='60' cy='60' r='54' strokeWidth='6'></circle>
                    <circle className={styles.bar_animate} cx='60' cy='60' r='54' strokeWidth='6'></circle>
                  </svg>
                  <strong className={styles.text}></strong>
                </div>
              </div>
              <div className={styles.big_figma}></div>
              </div>
            </div>


          <div>
            <div className={styles.box2}>

            </div>
          </div>

           
          </div>


          <div className={styles.auction_details}>

          </div>
          <div className={styles.auction_details}>

          </div>
        </div>
    </div>
  );
};




export default AuctionComingSoon;
