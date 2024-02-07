'use client'
import React, { useState } from 'react';
import axios from 'axios';
import styles from './InputLogin.module.scss';
import Link from 'next/link';
import { FaUser, FaLock } from 'react-icons/fa';
import { useRouter } from 'next/navigation';
import { signIn } from 'next-auth/react';

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

  const handleLogin = async (e) => {
    e.preventDefault();
    // const body = {
    //   username:username,
    //   password:password,
    //   redirect: false,
    // }
    // 로그인 처리 로직 추가
    // const backendUrl = 'https://i10a207.p.ssafy.io/api'
    // console.log(`Logging in as ${isArtist ? 'Artist' : 'User'}`);

    try {
      const data = await signIn('credentials', body);
      
      if (data.error) {
        alert(data.error);
        navigate.reload();
      }
      else {
        console.log(data);
        alert("로그인 성공");
        navigate.push('/');
      }
      
    } catch (error) {
        console.log(error);
    }

    // axios 요청 넣어봄
  //   if (isArtist) {

  //     const loginData = {
  //       "id": username,
  //       "pw": password,
  //     };
  //     // axios 요청 넣어봄

  //     axios.post(`${backendUrl}/artists/login`, loginData, {
  //       headers: {
  //         'Content-Type': 'application/json;charset=UTF-8'
  //       }
  //     })
  //       .then(response => {

  //         if (response.data === 'Login successful') {
  //           console.log('로그인 성공 :', response.data);
  //           navigate.push('/');
  //         }
  //         else {
  //           console.log('로그인 실패 :', response.data);
  //           setShowAlert(true);
  //         }
  //       })
  //       .catch(error => {
  //         console.error('로그인 실패(에러)', error.response ? error.response.data : error.message);
  //         // 로그인 실패하면 팝업 표시할 것
          
  //       });
  // } else {
  //   const loginData = {
  //     "id": username,
  //     "pw": password,
  //   };

  //   console.log(loginData);

  //   // axios 요청 넣어봄
  //   axios.post(`${backendUrl}/members/login`, loginData, {
  //     headers: {
  //       'Content-Type': 'application/json;charset=UTF-8'
  //     }
  //   }).then(response => {

  //     if (response.data === 'Login successful') {
  //       console.log('로그인 성공 :', response.data);
  //       navigate.push('/');
  //     }
  //     else {
  //       console.log('로그인 실패 :', response.data);
  //       setShowAlert(true);
  //     }
  //   })
  //   .catch(error => {
  //     console.error('로그인 실패(에러)', error.response ? error.response.data : error.message);
  //     // 로그인 실패하면 팝업 표시할 것
      
  //   });
//}

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
            style={{ backgroundColor: isArtist ? '#7377b6' : 'white',
            color: isArtist ? 'white' : 'black' }}
            >
            작가</button>
        </label>
        <br />

        <label>
          <button type='button'
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

    </div>
  );
};

export default InputLogin;