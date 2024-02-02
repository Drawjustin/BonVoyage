'use client'
import React from 'react'
import Navbar from '@/components/Navbar/Navbar';
import styles from '@/components/InputLogin/InputLogin.module.scss'
import Link from 'next/link';
import { FaUser, FaLock } from 'react-icons/fa';
import { signIn } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import axios from 'axios';

const LoginPage = () => {

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
      
        const handleLogin = async () => {

          console.log(username, password);
      
          const backendUrl = "http://43.200.244.3:8001";

          //     // axios 요청 넣어봄
    if (isArtist) {

      const loginData = {
        "artistId": username,
        "artistPwd": password,
      };
      // axios 요청 넣어봄
      axios.post(`${backendUrl}/artists/login`, loginData, {
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
        }
      })
        .then(response => {

          if (response.data === 'Login successful') {
            alert('로그인에 성공하였습니다.');
            sessionStorage.setItem('isLoggedIn', 'true');
            navigate.push('/');
          }
          else {
            alert('로그인에 실패하였습니다.');
            setShowAlert(true);
          }
        })
        .catch(error => {
          alert('로그인 실패(에러)', error.response ? error.response.data : error.message);
          // 로그인 실패하면 팝업 표시할 것
          
        });
  } else {
    const loginData = {
      "memberId": username,
      "memberPwd": password,
    };
    // axios 요청 넣어봄
    axios.post(`${backendUrl}/members/login`, loginData, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      }
    }).then(response => {

      if (response.data === 'Login successful') {
        alert('로그인에 성공하였습니다.');
        sessionStorage.setItem('isLoggedIn', 'true');
        navigate.push('/');
      }
      else {
        alert('로그인에 실패하였습니다.');
        setShowAlert(true);
      }
    })
    .catch(error => {
      alert('로그인 실패(에러)', error.response ? error.response.data : error.message);
      // 로그인 실패하면 팝업 표시할 것
      
    });
}
          
            

          // try {
          //   const data = await signIn('credentials', body);
          // } catch (error) {
          //     console.log(error);
          // }
        };
      
        const handleAlertClose = () => {
          // 팝업 닫기 및 상태 초기화
          setShowAlert(false);
          setUsername('');
          setPassword('');
        };

    return (
        <div>
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