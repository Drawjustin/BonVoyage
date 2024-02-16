// src/components/PaymentForm.jsx

import React, { useState } from 'react';
import axios from 'axios';

const KAKAO_API_KEY = '41B27CD1E0CE97520743';

const Kakaopay = () => {
  const [itemName, setItemName] = useState('');
  const [amount, setAmount] = useState('');

  const handlePayment = async () => {
    try {
      const response = await axios.post(
        'https://i10a207.p.ssafy.io/payment', // 백엔드 서버 주소
        { itemName, amount },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );

      // 카카오페이 결제 페이지로 리다이렉션
      window.location.href = response.data.redirect_url;
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <h1>카카오페이 결제</h1>
      <div>
        <label>Item Name:</label>
        <input
          type="text"
          value={itemName}
          onChange={(e) => setItemName(e.target.value)}
        />
      </div>
      <div>
        <label>Amount:</label>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
      </div>
      <button onClick={handlePayment}>Pay with KakaoPay</button>
    </div>
  );
};

export default Kakaopay;
