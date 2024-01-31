import React, { useState } from 'react'
// import { Calendar } from 'react-native-calendars';
import { Calendar } from 'react-calendar';
// import { styled } from 'styled-components';
import moment from 'moment';
import 'react-calendar/dist/Calendar.css'
import './Calendar.css'
import styles from './ArtistAuctionCalendar.module.scss'

const Calendar1 = () => {
  const [value, onChange] = useState(new Date());

  const dayList = [
    '2024-01-05',
    '2024-01-21',
  ];

  // 날짜 컨텐츠를 생성하는 함수
  const generateDateContent = (date) => {
    const contents = [];

    if (dayList.find((day) => day === moment(date).format('YYYY-MM-DD'))) {
      contents.push(
        <div key={moment(date).format('YYYY-MM-DD')} className={styles.is_dot}>
          <div className={styles.dot}></div>
        </div>
      );
    }

    return <div>{contents}</div>;
  };

  return (
      <div className={styles.container}>
      <div className={styles.title}>🗓️ 경매 일정</div>
      <div className={styles.schedule}>
        <Calendar
          onChange={onChange}
          value={value}
          formatDay={(locale, date) => date.toLocaleString('en', { day: 'numeric' })}
          // className={styles.calendar}
          calendarType="gregory"
          tileContent={({ date }) => generateDateContent(date)}
        />
        <div className={styles.schedule_list}>
          <div className={styles.schedule_title}>📍 경매 일정</div>
          <div className={styles.schedule_product}>
            <div className={styles.product_img}>
              {/* 상품 사진 */}
            </div>
            <div className={styles.product_info}>
              <p>작가 ㅇㅇㅇ</p>
              <p>상품명</p>
              <p>1월 24일 18:00</p>  
            </div>
          </div>
          <div className={styles.schedule_product}>
            <div className={styles.product_img}>
              {/* 상품 사진 */}
            </div>
            <div className={styles.product_info}>
              <p>작가 ㅇㅇㅇ</p>
              <p>상품명</p>
              <p>1월 24일 18:00</p>  
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Calendar1;