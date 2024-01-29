'use client'
import React, { useState } from 'react';
import axios from 'axios';
import styles from './InputLogin.module.scss';
import Link from 'next/link';
import { FaUser, FaLock } from 'react-icons/fa';
import { useRouter } from 'next/navigation';

const InputLogin = () => {
  const [isArtist, setIsArtist] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showAlert, setShowAlert] = useState(false);
  const navigate = useRouter();

  const handleArtistToggle = () => {
    setIsArtist(true);
  };

  const handlePersonalToggle = () => {
    setIsArtist(false);
  };

  const handleLogin = () => {

    // 로그인 처리 로직 추가
    const backendUrl = 'BACKEND_URL'
    // console.log(`Logging in as ${isArtist ? 'Artist' : 'User'}`);

    const loginData = {
      username: username,
      password: password,
      isArtist: isArtist,
    };

    // axios 요청 넣어봄
    axios.post(`${backendUrl}/login`, loginData)
      .then(response => {
        console.log('로그인 성공 :', response.data);
        // 로그인 성공하면 홈페이지로 이동시킬것
        navigate.push('/');
      })
      .catch(error => {
        console.error('로그인 실패', error.response ? error.response.data : error.message);
        // 로그인 실패하면 팝업 표시할 것
        setShowAlert(true);
      });
  };

  const handleAlertClose = () => {
    // 팝업 닫기 및 상태 초기화
    setShowAlert(false);
    setUsername('');
    setPassword('');
  };

  return (
    <div className={styles.login_container}>
      {showAlert && (
        <div className={styles.alert}>
          <p className={styles.pp}>로그인이 실패하였습니다.</p>
          <button className={styles.b} onClick={handleAlertClose}>확인</button>
        </div>
      )}
      <h2>Login</h2>
      <div className={styles.toggle_container}>
        <label>
          <button 
            onClick={handleArtistToggle}
            style={{ backgroundColor: isArtist ? '#7377b6' : 'white',
            color: isArtist ? 'white' : 'black' }}
            >
            작가</button>
        </label>
        <br />

        <label>
          <button
            onClick={handlePersonalToggle}
            style={{ backgroundColor: !isArtist ? '#7377b6' : 'white',
            color: !isArtist ? 'white' : 'black' }}
          >
          개인
          </button>
        </label>

        <br />

      </div>

      <div className={styles.container}>
      <div className={styles.input_container}>
        <div className={styles.icon_input}>
          <FaUser className={styles.icon} />
          <input
            type="text"
            placeholder={isArtist ? '작가 ID' : '개인 ID'}
          />
        </div>

        <div className={styles.icon_input}>
          <FaLock className={styles.icon} />
          <input
            type="password"
            placeholder="Password"
            />
        </div>
      </div>
      <div className={styles.button_container}>
      <button className={styles.login_button} onClick={handleLogin}>
        로그인
      </button></div>
    </div>
      
      <div className={styles.search_user_info_div}>
        <Link href='/FindId'>아이디 찾기</Link>
        <span> | </span>
        <Link href='/ResetPw'>비밀번호 찾기</Link>
      </div>

    </div>
  );
};

export default InputLogin;