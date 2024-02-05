'use client'
import React from "react";
import styles from './AuctionListPage.module.scss';
import Navbar from '../../components/Navbar/Navbar';


const AuctionListPage = () => {

    return (
        <div style={{ width: '1200px' }}>
        <Navbar />
            <div style={{ marginTop: '10vh' }}>
            <h1 style={{ color: '#f1efee'}}>경매 목록</h1>
            </div>
        </div>
    )
}

export default AuctionListPage