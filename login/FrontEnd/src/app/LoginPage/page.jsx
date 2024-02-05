import React from 'react'
import Navbar from '@/components/Navbar/Navbar';
import styles from '@/components/InputLogin/InputLogin.module.scss'
import Link from 'next/link';
import { FaUser, FaLock } from 'react-icons/fa';
import { signIn } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import axios from 'axios';
import KakaoLoginButton from './KakaoLoginButton';
import GoogleLoginButton from './GoogleLoginButton';
import InputLogin from '@/components/InputLogin/InputLogin';

const LoginPage = () => {




        const [loading, setLoading] = useState(false);
        const [active, setActive] = useState(false);

        const handleButtonClick = () => {
          setLoading(true);
          setTimeout(() => {
            setActive(true);
          }, 1500);
        };




        const [loading, setLoading] = useState(false);
        const [active, setActive] = useState(false);

        const handleButtonClick = () => {
          setLoading(true);
          setTimeout(() => {
            setActive(true);
          }, 1500);
        };

    return (
      
        <div style={{ marginTop: "25vh" }}>
            <Navbar />
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
            style={{ backgroundColor: isArtist ? '#171de5' : '#f1efee',
            color: isArtist ? 'white' : 'black' }}
            >
            작가</button>
        </label>
        <br />

        <label>
          <button
            onClick={handlePersonalToggle}
            style={{ backgroundColor: !isArtist ? '#171de5' : '#f1efee',
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
            value={username}
            onChange={(e) => {
              setUsername(e.target.value);
            }
          }
          />
        </div>

        <div className={styles.icon_input}>
          <FaLock className={styles.icon} />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
            }
          }
            />
        </div>
      </div>
      <div className={styles.button_container}>
      <button className={styles.login_button} onClick={handleLogin}>
        로그인
      </button></div>
    </div>

    <div>
      <KakaoLoginButton />
      <GoogleLoginButton />

    </div>
      
      <div className={styles.search_user_info_div}>
        <Link href='/FindId'>아이디 찾기</Link>
        <span> | </span>
        <Link href='/ResetPw'>비밀번호 찾기</Link>
      </div>

    </div>
        </div>




    )
      }

export default LoginPage