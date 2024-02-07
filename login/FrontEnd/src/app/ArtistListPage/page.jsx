import React from 'react';
import dynamic from 'next/dynamic';
import { Provider } from 'react-redux';
import store from '@/store';
import Navbar from '../../components/Navbar/Navbar';
import Categories from '@/components/categories/Categories/Categories';
import axios from 'axios';

const ArtistListPage = () => {

  async function Handle() {
    ArtistList = axios.get('https://i10a207.p.ssafy.io/api/artists');
    console.log(ArtistList);
  }
  

  return (
    <div style={{ width: '1200px' }}>
      <Navbar />
      <div style={{ marginTop: '10vh' }}>
        <h1 style={{ color: '#f1efee'}}>작가 목록</h1>
      </div>
    </div>
  );
};

export default ArtistListPage;