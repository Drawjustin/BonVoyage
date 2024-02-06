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
import KakaoLoginButton from './KakaoLoginButton';
import GoogleLoginButton from './GoogleLoginButton';
import InputLogin from '@/components/InputLogin/InputLogin';

const LoginPage = () => {

  // const [isArtist, setIsArtist] = useState(false);
  // const [username, setUsername] = useState('');
  // const [password, setPassword] = useState('');
  // const [showAlert, setShowAlert] = useState(false);
  // const navigate = useRouter();

  // const handleArtistToggle = () => {
  //   setIsArtist(true);
  // };

  // const handlePersonalToggle = () => {
  //   setIsArtist(false);
  // };

  // const handleLogin = async () => {
    
  //   const body = {
  //     username:username,
  //     password:password
  //   }

    //const backendUrl = "https://i10a207.p.ssafy.io:80/api";

    //     // axios 요청 넣어봄
//     if (isArtist) {

//       const loginData = {
//         "artistId": username,
//         "artistPwd": password,
//       };
//       // axios 요청 넣어봄
//       axios.post(`${backendUrl}/artists/login`, loginData, {
//         headers: {
//           'Content-Type': 'application/json;charset=UTF-8'
//         }
//       })
//         .then(response => {

//           if (response.data) {
//             alert('로그인에 성공하였습니다.');
//             sessionStorage.setItem('isLoggedIn', 'true');
//             navigate.push('/');
//           }
//           else {
//             alert('로그인에 실패하였습니다.');
//             setShowAlert(true);
//           }
//         })
//         .catch(error => {
//           alert('로그인 실패(에러)', error.response ? error.response.data : error.message);
//           // 로그인 실패하면 팝업 표시할 것
    
//         });
//   } else {
//     const loginData = {
//       "memberId": username,
//       "memberPwd": password,
//     };
//     // axios 요청 넣어봄
//     axios.post(`${backendUrl}/members/login`, loginData, {
//       headers: {
//         'Content-Type': 'application/json;charset=UTF-8'
//       }
//     }).then(response => {

//       if (response.data === 'Login successful') {
//         alert('로그인에 성공하였습니다.');
//         sessionStorage.setItem('isLoggedIn', 'true');
//         navigate.push('/');
//       }
//       else {
//         alert('로그인에 실패하였습니다.');
//         setShowAlert(true);
//       }
//     })
//     .catch(error => {
//       alert('로그인 실패(에러)', error.response ? error.response.data : error.message);
//       // 로그인 실패하면 팝업 표시할 것

//     });
// }
    
      

  //   try {
  //     console.log("바디", body);
  //     const data = await signIn('credentials', body);
  //     console.log("데이터", data);
  //     if (data) {
  //         alert("로그인 성공.");
  //         navigate.push('/');              
  //     }
  //     else {
  //       alert("로그인 실패");
  //     }

  //   } catch (error) {
  //       console.log(error);
  //   }
  // };

  // const handleAlertClose = () => {
  //   // 팝업 닫기 및 상태 초기화
  //   setShowAlert(false);
  //   setUsername('');
  //   setPassword('');
  // };

  //       const [loading, setLoading] = useState(false);
  //       const [active, setActive] = useState(false);

  //       const handleButtonClick = () => {
  //         setLoading(true);
  //         setTimeout(() => {
  //           setActive(true);
  //         }, 1500);
  //       };

    return (
      
        
    <div>

    <div>
      <Navbar/>
      <InputLogin/>
      <KakaoLoginButton />
      <GoogleLoginButton />

    </div>
      
      <div className={styles.search_user_info_div}>
        <Link href='/FindId'>아이디 찾기</Link>
        <span> | </span>
        <Link href='/ResetPw'>비밀번호 찾기</Link>
      </div>

    </div>




    )
      }

export default LoginPage