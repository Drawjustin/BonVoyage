import React from 'react';
import dynamic from 'next/dynamic';
import { Provider } from 'react-redux';
import Navbar from '../../components/Navbar/Navbar';
import axios from 'axios';
import styles from './ArtistListPage.module.scss'

const ArtistListPage = () => {

  const Artists = {} // await axios.get('https://i10a207.p.ssafy.io/api/artists');
    // console.log(Artists.data);

  return (
    <div style={{ width: '1200px' }}>
      <Navbar />
      <div style={{ marginTop: '10vh' }}>
        <h1 style={{ color: '#f1efee'}}>작가 목록</h1>
      </div>

      <div className={styles.grid}>
              {Artists.data?.map((Artist) =>
                  <ProductCard
                    currentUser={'퍄퍄퍄'}
                    key={Artists.itemSeq}
                    data={product}
                  />)}
              </div>
    </div>
  );
};

export default ArtistListPage;