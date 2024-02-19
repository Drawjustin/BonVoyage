import React from 'react'
import styles from './ArtistReview.module.scss'

const ArtistReview = () => {
  return (
      <div className={styles.container}>
        <div className={styles.content}>
          <div className={styles.title}>🙂 작가의 리뷰</div>
          <div className={styles.review_top}>
            <div></div>
            <div></div>
            <div></div>
          </div>
          <div className={styles.review_all}>
            {/* <div className={styles.review_list}> */}
              <div className={styles.review}>
                <div className={styles.review_img}>
                </div>
                <div className={styles.review_info}>
                  <p>상품명</p>
                  <p>좋아요 어쩌구저쩌구...</p>
                </div>
              </div>
              <div className={styles.review}>
                <div className={styles.review_img}>
                </div>
                <div className={styles.review_info}>
                  <p>상품명</p>
                  <p>후기 글</p>
                </div>
              </div>
              <div className={styles.review}>
                <div className={styles.review_img}>
                </div>
                <div className={styles.review_info}>
                  <p>상품명</p>
                  <p>후기 글</p>
                </div>
              </div>
              <div className={styles.review}>
                <div className={styles.review_img}>
                </div>
                <div className={styles.review_info}>
                  <p>상품명</p>
                  <p>후기 글</p>
                </div>
              </div>
            {/* </div> */}
          </div>
        </div>
      </div>
  )
}

export default ArtistReview;
