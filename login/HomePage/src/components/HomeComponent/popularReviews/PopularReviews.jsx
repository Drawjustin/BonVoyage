'use client'
import React, {useState} from 'react';
import Link from 'next/link';
import styles from '../HomeComponent.module.scss';

const PopularReviews = () => {
  // 가데이터 대충 만들기
  const PopularReviewDatas = [
    { title: '나의 농장', artist: '고구마' },
    { title: '멍멍', artist: '갱얼쥐' },
    { title: '해피캣', artist: '고얌미' },
    { title: '푸바오에게', artist: '가지마' },
    { title: '룰루', artist: '랄라' },
    { title: '녹차', artist: '아이스티' },
    { title: '쇼콜라', artist: '바닐라' },
    { title: '치즈인더트랩', artist: '순끼' },
    { title: '작전명 순정', artist: '맘마미아' },
    { title: '보헤미안랩소디', artist: '퀸' },
    { title: '눈물나는', artist: '양파' },
    { title: '냄새나는..', artist: '마늘' },
    { title: '커피', artist: '중독' },
    { title: '아이폰', artist: '앱등이' },
    { title: '바나', artist: '프레소' },
    { title: '데스노트', artist: '라이토' }
  ];

  const [itemsPerRow, setItemsPerRow] = useState(3);

  const handleSetThreePerRow = () => {
    setItemsPerRow(3);
  };

  const handleSetFourPerRow = () => {
    setItemsPerRow(4);
  };

  return (
    <div className={styles.review_list}>
      <div className={styles.title_container}>
        <Link href='/review'><h1 className={styles.popular_reviews}>인기 리뷰</h1></Link>
        <div className={styles.more_container}>
        <Link href='/review' className={styles.more_link}>더보기</Link>
        </div>
      </div>
      
      <div className={styles.toggle_buttons}>
        <button onClick={handleSetThreePerRow} className={itemsPerRow === 3 ? styles.active_button : ''}>⚂</button>
        <button onClick={handleSetFourPerRow} className={itemsPerRow === 4 ? styles.active_button : ''}>⚃</button>
      </div>
        
   

      <div className={styles.grid_container}>
        {PopularReviewDatas.map((PopularReviewData, index) => (
          <div key={index} className={styles.product_item} style={{ width: `calc(${100 / itemsPerRow}% - 20px)` }}>
            <div className={styles.image_placeholder}></div>
            <div className={styles.text_container}>
              <p className={styles.title}>{PopularReviewData.title}</p>
              <p className={styles.artist}>작가 | {PopularReviewData.artist}</p>
            </div>
          </div>
          ))}
      </div>
    </div>
  );
};

export default PopularReviews
