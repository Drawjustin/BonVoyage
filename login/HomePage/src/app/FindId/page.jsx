'use client'
import React, { useState } from 'react';
import styles from './FindId.module.scss';


const FindId = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [foundId, setFoundId] = useState('');

  const handleFindId = () => {
    // 실제 아이디 찾기 로직 구현
    // 서버에 axios 요청 후 해당 정보와 일치하는 아이디 받아오기
    const combinedInfo = `${name}-${email}-${phone}`;
    setFoundId(combinedInfo);
  };

  return(
    <div className={styles.id_finder_container}>
      <h2>아이디 찾기</h2>
    

  <div className={styles.container}>

      <div className={styles.input_container}>
        <label htmlFor="name">이름</label>
        <input type="text" placeholder='이름을 입력하세요.' id='name' name='name' value={name} onChange={(e) => setName(e.target.value)} />
      </div>

      <div className={styles.input_container}>
        <label htmlFor="email">이메일</label>
        <input type="email" placeholder='이메일을 입력하세요.'  id='email' name='email' value={email} onChange={(e) => setEmail(e.target.value)} />
      </div>

      <div className={styles.input_container}>
        <label htmlFor="phone">전화번호</label>
        <input type="tel" placeholder='전화번호를 입력하세요.'  id='phone' name='phone' value={phone} onChange={(e) => setPhone(e.target.value)} />
      </div>

      <div className={styles.button_container}>
      <button className={styles.find_button} onClick={handleFindId}>찾기</button>
      </div>
  </div>

      {foundId && (
        <div className={styles.result_container}>
          <p>찾은 아이디: {foundId}</p>
        </div>
      )}

    </div>
  )
};   


export default FindId
