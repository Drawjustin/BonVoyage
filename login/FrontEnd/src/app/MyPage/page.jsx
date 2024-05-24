'use client'
import React, { useEffect, useState } from 'react'
import styles from './MyPage.module.scss'
import Rodal from 'rodal';
import 'rodal/lib/rodal.css';

// import { Link } from 'react-router-dom';
import ChargePoint from '../../components/MyPageComponent/ChargePoint/ChargePoint';
import Navbar from '../../components/Navbar/Navbar';

import accountinfo from './1.png';
import purchaseinfo from './2.png';
import faq from './3.png';
import myauctioncal from './4.png';
import dynamic from 'next/dynamic';
import getCurrentUser from '@/app/actions/getCurrentUser';
import { current } from '@reduxjs/toolkit';
const AccountInfo = dynamic(() => import('../../components/MyPageComponent/AccountInfo/AccountInfo'), {ssr:false});
const PurchaseInfo = dynamic(() => import('../../components/MyPageComponent/PurchaseInfo/PurchaseInfo'), {ssr:false});
const Faq = dynamic(() => import('../../components/MyPageComponent/Faq/Faq'), {ssr:false});
const MyAuctionCal = dynamic(() => import('../../components/MyPageComponent/MyAuctionCal/MyAuctionCal'), {ssr:false});

// import React from 'react';
// import {
//   Button,
//   Cascader,
//   DatePicker,
//   Form,
//   Input,
//   InputNumber,
//   Mentions,
//   Select,
//   TreeSelect,
// } from 'antd';


const MyPage = () => {
    const currentUser = getCurrentUser();
    const [profileData, setProfileData] = useState({
        level: 'Basic',
        point: 1000,

    });

    const [profilePicture, setProfilePicture] = useState(null);
    const [visible, setVisible] = useState(false);

        const show = () => {
            setVisible(true);
        };

        const hide = () => {
            setVisible(false);
        };

        const updateRemainingPoints = (newRemainingPoints) => {
            setRemainingPoints(newRemainingPoints);
        };


    // 페이지 로드될 때 로컬 저장소에서 프로필 사진 불러옴
    useEffect(() => {
        const storedProfilePicture = localStorage.getItem('profilePicture');
        if (storedProfilePicture) {
            setProfilePicture(storedProfilePicture);
        }
        
        const fileInput = document.getElementById('fileInput');
        if (fileInput) {
            fileInput.style.display = 'none';
        }
    }, []);

    const handleFileChange = (event) => {
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();

            reader.onload = (e) => {
                // 프로필 사진을 로컬 스토리지에 저장
                localStorage.setItem('profilePicture', e.target.result);
                setProfilePicture(e.target.result);
            };

            reader.readAsDataURL(file);
        }

    };


    const [activeComponent, setActiveComponent] = useState(null);

    const handleButtonClick = (componentType) => {
        setActiveComponent(componentType);
    };
    

    return (
        
        <div className={styles.container}>

        <div className={styles.my_page}>
        <h2 style={{textAlign: 'left', color: '#f1efee' }}>마이 페이지</h2>
            <div className={styles.profile_container}>
                <div className={styles.profile_picture_container} onClick={() => document.getElementById('fileInput').click()}>
                    <div className={styles.profile_picture} style={{ backgroundImage: `url(${profilePicture})` }}>
                        {/* 기존 프로필 이미지 표시 */}
                    </div>
                    <input type="file" id='fileInput' onChange={handleFileChange} />
                </div>

                <div className={styles.right_section}>
                    <div className={styles.membership_info}>
                        ⛵

                        <div className={styles.membership_grade}>{profileData.level} 등급</div>
                    </div>
                    <div className={styles.points_info}>
                        포인트 : {profileData.point}
                    </div>
                    <button className={styles.charge_points_button} onClick={show}>
                        포인트 충전
                    </button>
                    <Rodal visible={visible} onClose={hide}
                            customStyles={{
                                width: '600px', 
                                height: '400px',
                                padding: '0px 0px',
                                borderRadius: '20px' 
                            }}>
                    <ChargePoint updateRemainingPoints={updateRemainingPoints} />
                    </Rodal>
                </div>
            </div>
            <div className={styles.navigation_container}>
                <div className={styles.nav_button} onClick={() => handleButtonClick('accountInfo')}>
                    <img className={styles.image} src={accountinfo} alt="계정 정보" />
                    <div style={{ color: '#f1efee', fontFamily: 'Gowun Dodum' }}>계정 정보</div>
                </div>
                    
                <div className={styles.nav_button} onClick={() => handleButtonClick('purchaseInfo')}>
                    <img className={styles.image} src={purchaseinfo} alt="구매 내역" />
                    <div style={{ color: '#f1efee', fontFamily: 'Gowun Dodum' }}>구매 내역</div>
                </div>
                
                <div className={styles.nav_button} onClick={() => handleButtonClick('faq')}>
                    <img className={styles.image} src={faq} alt="FAQ" />
                    <div style={{ color: '#f1efee', fontFamily: 'Gowun Dodum' }}>FAQ</div>
                </div>
                
                <div className={styles.nav_button} onClick={() => handleButtonClick('myAuctionCal')}>
                    <img className={styles.image} src={myauctioncal} alt="나의 경매 일정" />
                    <div style={{ color: '#f1efee', fontFamily: 'Gowun Dodum' }}>나의 경매 일정</div>
                </div>
            
            </div>
        </div>
        {activeComponent === 'accountInfo' && <AccountInfo isArtist={currentUser}/>}
        {activeComponent === 'purchaseInfo' && <PurchaseInfo />}
        {activeComponent === 'faq' && <Faq />}
        {activeComponent === 'myAuctionCal' && <MyAuctionCal />}
    </div>


    )
}

export default MyPage