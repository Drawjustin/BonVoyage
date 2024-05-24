'use client'
import React, { useEffect } from 'react';
import KakaoLogin from 'react-kakao-login';
import kakaobutton from './kakao_login.png';
import styles from './button.module.scss';


const KakaoLoginButton = () => {
  const REST_API_KEY = 'a3002d14622e3f0380776d5aed15a26d'
  const redirect_uri = 'https://i10a207.p.ssafy.io/api/member/login/kakao';

  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${redirect_uri}&response_type=code`
  const handleLogin = ()=>{
    window.location.href = kakaoURL
  }


  return (
      <div>
        
        <img className={styles.b} src={kakaobutton} onClick={handleLogin} alt="카카오버튼" />
      </div>

  );
};

export default KakaoLoginButton;
