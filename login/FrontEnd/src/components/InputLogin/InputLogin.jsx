'use client'
import React, { useState } from 'react';
import axios from 'axios';
import styles from './InputLogin.module.scss';
import Link from 'next/link';
import { FaUser, FaLock } from 'react-icons/fa';
import { useRouter } from 'next/navigation';
import { signIn } from 'next-auth/react';
import KakaoLoginButton from './KakaoLoginButton';
import GoogleLoginButton from './GoogleLoginButton';
import SocialKakao from './SocialKakao';

const InputLogin = () => {
  
  const [isArtist, setIsArtist] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showAlert, setShowAlert] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useRouter();

  const handleArtistToggle = () => {
    setIsArtist(true);
  };

  const handlePersonalToggle = () => {
    setIsArtist(false);
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    const body = {
      username:username,
      password:password,
      callbackUrl: "/",
    }
    // 로그인 처리 로직 추가
    try {
      const backendUrl = "https://i10a207.p.ssafy.io/api";
      // Artist
      if(isArtist) {
        const artistBody = {
            id: body.username,
            pw: body.password
        }

        const userResponse = await axios.post(`${backendUrl}/artists/login`, artistBody, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        });

        if (userResponse.data === "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.") {
          alert('로그인 실패');
          return;
        } else {
          alert('로그인 성공')
          sessionStorage.setItem('session', JSON.stringify({id : userResponse.data, role: 'artist'}));
          navigate.push('/');
        }
        
      } else {
          // Member

        const memberBody = {
          id: body.username,
          pw: body.password
      }

        const memberResponse = await axios.post(`${backendUrl}/members/login`, memberBody, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        });

        if (memberResponse.data === "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.") {
          alert('로그인 실패');
          return;
        }

        alert('로그인 성공')
        sessionStorage.setItem('session', JSON.stringify({id : memberResponse.data, role: 'member'}));
        navigate.push('/');
      }
      
        
      } catch (error) {
      console.log('에러에러', error);
      alert('로그인 실패');
      return null;
    } finally {
      setIsLoading(false);
    }

  }


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
          <button type='button'
            onClick={handleArtistToggle}
            style={{ backgroundColor: isArtist ? '#171de5' : '#f1efee',
            color: isArtist ? '#f1efee' : '#171de5' }}
            >
            작가</button>
        </label>
        <br />

        <label>
          <button type='button'
            onClick={handlePersonalToggle}
            style={{ backgroundColor: !isArtist ? '#171de5' : '#f1efee',
            color: !isArtist ? '#f1efee' : '#171de5' }}
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
            onChange={(e)=>setUsername(e.target.value)}
          />
        </div>

        <div className={styles.icon_input}>
          <FaLock className={styles.icon} />
          <input
            type="password"
            placeholder="Password"
            onChange={(e)=>setPassword(e.target.value)}
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
      
      {/* <div style={{ marginTop: '10px' }}>
        <SocialKakao />
        <GoogleLoginButton />
      </div> */}

    </div>
  );
};

export default InputLogin;
