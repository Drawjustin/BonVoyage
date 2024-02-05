'use client'
import React from 'react';
import KakaoLogin from 'react-kakao-login';
import kakaobutton from './kakao_login.png';
import styles from './button.module.scss';


const KakaoLoginButton = ({ onLoginSuccess, onLoginFailure }) => {
  const KAKAO_APP_KEY = '3b5b638c90307bb8253e7f6db8706b63'
  return (
    <KakaoLogin
      token={KAKAO_APP_KEY}
      onSuccess={onLoginSuccess}
      onFail={onLoginFailure}
      render={(props) => (
        <img className={styles.b} src={kakaobutton} onClick={props.onClick} alt="카카오버튼" />
      )}
    />
  );
};

export default KakaoLoginButton;
