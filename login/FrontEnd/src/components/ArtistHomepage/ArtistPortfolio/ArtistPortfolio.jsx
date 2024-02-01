'use client'
import React, { useState } from 'react'
import styles from './ArtistPortfolio.module.scss'
import { Timeline } from 'antd';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider1 from '../Slider1/Slider1';

import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import 'swiper/css/scrollbar';

const ArtistPortfolio = () => {
  const [isEditMode, setIsEditMode] = useState(false);
  const [newItemText, setNewItemText] = useState('');
  // const timelineItems = [
  const [timelineItems, setTimelineItems] = useState([
    {
      color: '#8FCACA',
      children: '2018 “코리안 팝아트”, 하남문화예술회관, 경기',
    },
    {
      color: '#8FCACA',
      children: '2017 “Soul of City” 특별전, UIA 2017 Seoul World Architects Congress, COEX Hall D, 서울',
    },
    {
      color: '#8FCACA',
      children: '2017 “미디어아트 인 울산 -과학과 예술의 융합”, 울산문화예술회관, 울산',
    },
    {
      color: '#8FCACA',
      children: '2016 “내 안의 또 다른 나”, 가일미술관, 경기',
    },
    {
      color: '#8FCACA',
      children: '2015 “사이공간”, 스페이스 비엠, 서울',
    },
  ]);

  const handleInputChange = (e) => {
    setNewItemText(e.target.value);
  };

  const handleAddItem = () => {
    if (newItemText.trim() !== '') {
      // 입력값이 비어있지 않을 경우에만 추가
      const newItem = {
        color: '#8FCACA',
        children: newItemText,
      };

      // axios.post('http://localhost:8080/api/timeline', newItem)
      //   .then((response) => {
      //     setTimelineItems((prevItems) => [...prevItems, response.data]);
      //   })
      //   .catch((error) => {
      //     console.error('Error:', error);
      //   });

      console.log(newItem)

      setTimelineItems((prevItems) => [...prevItems, newItem]);
      // 입력창 초기화
      setNewItemText('');
      setIsEditMode(false);
    }
  };

  return (
      <div className={styles.container}>
        <div className={styles.content_box}>
          <div className={styles.filmography} style={{ maxWidth: '600px', margin: 'auto', textAlign: 'center' }}>
            <div className={styles.slider_container}><Slider1 /></div>
          </div>
          <div className={styles.history}>
            <div className={styles.title_btn}>
              <div className={styles.title}>🎨 작가 이력</div>
              {isEditMode ? (
                <button className={styles.edit} onClick={() => setIsEditMode(false)}>
                  완료
                </button>
              ) : (
                <button className={styles.edit} onClick={() => setIsEditMode(true)}>
                  수정하기
                </button>
              )}
            </div>
            {isEditMode && (
            <>
              <div className={styles.add_element}>
                <input
                  type="text"
                  value={newItemText}
                  onChange={handleInputChange}
                  placeholder="항목 추가"
                  className={styles.add_input}
                />
                <button onClick={handleAddItem}>추가하기</button>
              </div>
            </>
          )}
            <Timeline className={styles.history_content}
                items={timelineItems}/>
          </div>
        </div>
      </div>
  )
}

export default ArtistPortfolio;
