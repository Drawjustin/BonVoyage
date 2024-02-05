'use client'

import React, { useState } from 'react'
import styles from '../mainpage3.module.scss'
import { Calendar } from 'react-calendar';
import 'react-calendar/dist/Calendar.css'
import moment from 'moment';
import { useRouter } from 'next/navigation';
import axios from 'axios';
// import { Link } from 'react-router-dom';

async function AuctionCal() {

  async function Handle(){
    const response = await axios.get(`https://i10a207.p.ssafy.io/api/auction`);
    console.log(response.data);
  }

  const navigate = useRouter();

  const [value, onChange] = useState(new Date());

  const generateDateContent = (date) => {
    const contents = [];

    contents.push(
      <React.Fragment key={moment(date).format('YYYY-MM-DD')}>
      </React.Fragment>
      );

    return <div>{contents}</div>;
  };

  return (
      <div><h1 style={{ fontSize: '30px', fontWeight: 'bolder', textAlign: 'left', color: '#f1efee' }}>경매 일정</h1>
        {/* <Nav /> */}
        <div className={styles.container}>
        <div className={styles.schedule}>
          
          <div>
            <Calendar
              onChange={onChange}
              value={value}
              formatDay={(locale, date) => date.toLocaleString('en', { day: 'numeric' })}
              className={styles.calendar}
              calendarType="gregory"
              tileContent={({ date }) => generateDateContent(date)}
            />
          </div>
          <div className={styles.schedule_list}>
            <div className={styles.schedule_title}>📍 오늘의 경매 일정</div>
            <div className={styles.schedule_product}>
              <div className={styles.product}>
                <div className={styles.isLive}>
                  <div className={styles.dot}></div>
                  <p>Live</p>
                </div>
                <div className={styles.product_}>
                  <div className={styles.product_img}>
                    {/* 상품 사진 */}
                  </div>
                  <div className={styles.product_e}>
                    <div className={styles.product_info} onClick={Handle}>
                      <p>작가 이바보야</p>
                      <p>진짜아니야</p>
                      <p>2월 5일 18:00</p> 
                    </div>
                      <button className={styles.auction_btn} onClick={() => navigate.push('/AuctionLivePage')}>참여</button>
                  </div>
                </div>
              </div>
            </div>
            <div className={styles.schedule_product}>
              <div className={styles.product}>
                <div className={styles.product_}>
                  <div className={styles.product_img}>
                    {/* 사진 */}
                  </div>
                  <div className={styles.product_e}>
                    <div className={styles.product_info}>
                      <p>작가 김싸피</p>
                      <p>포스터</p>
                      <p>2월 20일 18:00</p> 
                    </div>
                    <button className={styles.auction_btn} onClick={() => navigate.push('/AuctionLivePage')}>참여</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        </div>
      </div>
  )
}

export default AuctionCal;
