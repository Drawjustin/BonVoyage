'use client'
import React, {useState} from 'react';
import Link from 'next/link';
import styles from '../HomeComponent.module.scss';

import in1 from './in/in1.jpg';
import in2 from './in/in2.jpg';
import in3 from './in/in3.jpeg';
import in4 from './in/in4.png';
import in5 from './in/in5.jpg';
import in6 from './in/in6.jpg';
import in7 from './in/in7.jpg';
import in8 from './in/in8.jpg';
import in9 from './in/in9.jpg';
import in10 from './in/in10.png';
import in11 from './in/in11.jpg';
import in12 from './in/in12.jpg';
import in13 from './in/in13.jpg';
import in14 from './in/in14.jpg';
import in15 from './in/in15.jpg';
import in16 from './in/in16.png';

const PopularReviews = () => {
  // 가데이터 대충 만들기
  const PopularReviewDatas = [
    { title: '나의 농장', artist: '고구마', image: in1 },
    { title: '멍멍', artist: '갱얼쥐', image: in2 },
    { title: '해피캣', artist: '고얌미', image: in3 },
    { title: '푸바오에게', artist: '가지마', image: in4 },
    { title: '룰루', artist: '랄라', image: in5 },
    { title: '녹차', artist: '아이스티', image: in6 },
    { title: '쇼콜라', artist: '바닐라', image: in7 },
    { title: '치즈인더트랩', artist: '순끼', image: in8 },
    { title: '작전명 순정', artist: '맘마미아', image: in9 },
    { title: '보헤미안랩소디', artist: '퀸', image: in10 },
    { title: '눈물나는', artist: '양파', image: in11 },
    { title: '냄새나는..', artist: '마늘', image: in12 },
    { title: '커피', artist: '중독', image: in13 },
    { title: '아이폰', artist: '앱등이', image: in14 },
    { title: '바나', artist: '프레소', image: in15 },
    { title: '데스노트', artist: '라이토', image: in16 }
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
            <img src={PopularReviewData.image} alt={PopularReviewData.title} className={styles.image_placeholder} />

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