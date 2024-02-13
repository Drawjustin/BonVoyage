import React from 'react';
import dynamic from 'next/dynamic';
import { Provider } from 'react-redux';
import axios from 'axios';
import styles from './ArtistListPage.module.scss'
import getCurrentUser from '@/app/actions/getCurrentUser';
import ArtistCard from '@/components/Artists/ArtistCard/ArtistCard';

const ArtistListPage = async () => {

  const currentUser = await getCurrentUser();
  const Artists = {}; //await axios.get('https://i10a207.p.ssafy.io/api/artists');

  return (
    <div className="container" style={{ marginTop: '10px', marginLeft: '24vh' , width: '85%', alignItems: 'center' }}>

      <h1 style={{ color: '#f1efee', textAlign: 'left', marginBottom: '10px' }}>작가 목록</h1>


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