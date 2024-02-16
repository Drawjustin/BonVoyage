import './App.css'
import React from 'react';
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import HomePage from './pages/HomePage';

import LoginPage from './pages/LoginPage';
import FindId from './components/FindId/FindId';
import ResetPw from './components/ResetPw/ResetPw';

import SignupPage from './pages/SignupPage';
import SignupArtistPage from './pages/SignupArtistPage';
import SignupMemberPage from './pages/SignupMemberPage';

import MyPage from './pages/MyPage';
import './pages/HomePage/logo.png';
import Layout from './components/layout/Layout';
import ChargePoint from './components/ChargePoint/ChargePoint';
// import CartPage from './app/CartPage/CartPage';
import '../quill.custom.css';

import dynamic from 'next/dynamic';
const ThreejsMainPage = dynamic(() => import("../pages/ThreejsMainPage"), { ssr: false });

const App = () => {

  return (
  <>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Layout />}/>
        <Route index element = {<HomePage />} />
        <Route path="/signup" element={<SignupPage />} />
          <Route path="/signup/artist" element={<SignupArtistPage />} />
          <Route path="/signup/member" element={<SignupMemberPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path='/find_id' element={<FindId />} />
        <Route path='/reset_pw' element={<ResetPw />} />
        <Route path='/mypage' element={<MyPage />} />
        <Route path='/charge_point' element={<ChargePoint />} />
        <Route path='/threejs' element={<ThreejsMainPage />} />
      </Routes>
    </BrowserRouter>
  </>

  )
}

export default App
  