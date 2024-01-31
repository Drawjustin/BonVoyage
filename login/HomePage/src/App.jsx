import './App.css'
import React from 'react';
import { BrowserRouter, Routes, Route} from 'react-router-dom';
// import Navbar from './components/Navbar/Navbar';
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

const App = () => {

  return (
  <div>

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
      </Routes>
    </BrowserRouter>
  </div>

  )
}

export default App
  