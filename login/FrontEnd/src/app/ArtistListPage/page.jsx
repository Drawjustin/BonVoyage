import React from 'react';
import dynamic from 'next/dynamic';
import { Provider } from 'react-redux';
import Navbar from '../../components/Navbar/Navbar';
import axios from 'axios';
import styles from './ArtistListPage.module.scss'
import getCurrentUser from '@/app/actions/getCurrentUser';
import ArtistCard from '@/components/Artists/ArtistCard/ArtistCard';

const ArtistListPage = async () => {

  const currentUser = await getCurrentUser();
  const Artists = {}; //await axios.get('https://i10a207.p.ssafy.io/api/artists');

  return (
    <div style={{ width: '1200px' }}>
      <Navbar />
      <div style={{ marginTop: '10vh' }}>
        <h1 style={{ color: '#f1efee'}}>작가 목록</h1>
      </div>

      <div className={styles.grid}>
              {Artists?.data?.map((Artist) =>
                  <ArtistCard
                    currentUser={currentUser}
                    key={Artist.itemSeq}
                    data={Artist}
                  />)}
              </div>
    </div>
  );
};

export default ArtistListPage;