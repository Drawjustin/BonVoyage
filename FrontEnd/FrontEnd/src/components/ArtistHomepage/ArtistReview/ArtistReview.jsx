import React from 'react'
import styles from './ArtistReview.module.scss'

import img1 from './img/1.jpg'
import img2 from './img/2.jpg'
import img3 from './img/3.jpg'
import img4 from './img/1_1.jpg'
import img5 from './img/2_1.jpg'
import img6 from './img/3_1.jpg'
import img7 from './img/4_1.jpg'

const ArtistReview = () => {
  return (
      <div className={styles.container}>
        <div className={styles.content}>
          <div className={styles.title}>🙂 작가의 리뷰</div>
          <div className={styles.review_top}>
            <div>
              <img src={img1} />
            </div>
            <div>
            <img src={img2} />
            </div>
            <div>
            <img src={img3} />
            </div>
          </div>
          <div className={styles.review_all}>
            {/* <div className={styles.review_list}> */}
              <div className={styles.review}>
                <div className={styles.review_img}>
                <img src={img4} />
                </div>
                <div className={styles.review_info}>
                  <p>별이 빛나는 밤</p>
                  <p>너무 마음에 듭니다</p>
                </div>
              </div>
              <div className={styles.review}>
                <div className={styles.review_img}>
                <img src={img5} />
                </div>
                <div className={styles.review_info}>
                  <p>알렉산더</p>
                  <p>작품이 너무 예뻐요</p>
                </div>
              </div>
              <div className={styles.review}>
                <div className={styles.review_img}>
                <img src={img6} />
                </div>
                <div className={styles.review_info}>
                  <p>밤</p>
                  <p>감사합니다 집에 두었는데 너무 예뻐요</p>
                </div>
              </div>
              <div className={styles.review}>
                <div className={styles.review_img}>
                <img src={img7} />
                </div>
                <div className={styles.review_info}>
                  <p>모나리자</p>
                  <p>감사합니다</p>
                </div>
              </div>
            {/* </div> */}
          </div>
        </div>
      </div>
  )
}

export default ArtistReview;
