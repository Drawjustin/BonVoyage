'use client'
import React, { useState } from "react";
import styles from './PurchaseDetail.module.scss';
import 'rodal/lib/rodal.css';
import first_product from './first_product.jpg';

const PurchaseDetail = ({ isOpen, onClose }) => {

    const sampleData = {
        purchaseDate: '2024.01.26',
        productName: '우주 먼지 키링',
        price: '50000',
        orderNumber: '900911',
        orderDate: '2024.01.26',
        customerName: '김싸피',
        shippingAddress: '서울특별시 강남구 역삼동 테헤란로 212',
        phoneNumber: '010123456789',
        totalAmount: '50000',
        shippingFee: '3000',
    };
      
    return (
      <div style={{ fontFamily: 'Gowun Dodum' }}>
      <h3 className={styles.title}>결제 상세</h3>
      <div className={styles.purchase_details_container}>
        <div className={styles.product_details}>
          <img src={first_product} alt="Product" />
          <div className={styles.details}>
            <p style={{ color: 'gray', fontWeight: 'bold'}}>{sampleData.purchaseDate}</p>
            <p>{sampleData.productName}</p>
            <p>{sampleData.price}</p>
          </div>
        </div>
        <br />
        <div className={styles.order_info}>
          <div>
            <p><span style={{ fontWeight: 'bold'}}>주문번호</span> {sampleData.orderNumber}</p>
            <p><span style={{ fontWeight: 'bold'}}>주문일자</span> {sampleData.orderDate}</p>
          </div>
          <br />
          <div>
            <p><span style={{ fontWeight: 'bold'}}>주문자명</span> {sampleData.customerName}</p>
            <p><span style={{ fontWeight: 'bold'}}>배송지</span> {sampleData.shippingAddress}</p>
            <p><span style={{ fontWeight: 'bold'}}>전화번호</span> {sampleData.phoneNumber}</p>
          </div>
          <br />
          <div>
            <p><span style={{ fontWeight: 'bold'}}>합계 금액</span> {sampleData.totalAmount} (원)</p>
            <p><span style={{ fontWeight: 'bold'}}>배송비</span> {sampleData.shippingFee} (원)</p>
            <p><span style={{ fontWeight: 'bold'}}>총 결제 금액</span> {sampleData.totalPayment + sampleData.shippingFee}</p>
          </div>
        </div>
      </div>
    </div>

    )
}

export default PurchaseDetail