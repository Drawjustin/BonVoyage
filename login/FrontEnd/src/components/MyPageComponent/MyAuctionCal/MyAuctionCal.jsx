'use client'
import React, { useState } from "react";
import styles from './MyAuctionCal.module.scss';
import { Calendar } from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import moment from 'moment';
import axios from "axios";

const MyAuctionCal = () => {

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
        <div>
            <div className={styles.title}><h3 style={{ paddingLeft: '20px', color: '#f1efee', fontFamily: 'Gowun Dodum' }}>나의 경매 일정</h3></div>
            
            <div>
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
                            <div className={styles.product_info}>
                                <p>작가 ㅇㅇㅇ</p>
                                <p>상품명</p>
                                <p>1월 24일 18:00</p> 
                            </div>
                            {/* <Link to="/auctionlive"> */}
                            <button className={styles.auction_btn}>참여</button>
                            {/* </Link> */}
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
                            <p>작가 ㅇㅇㅇ</p>
                            <p>상품명</p>
                            <p>1월 24일 18:00</p> 
                            </div>
                            <button className={styles.auction_btn}>참여</button>
                        </div>
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

export default MyAuctionCal