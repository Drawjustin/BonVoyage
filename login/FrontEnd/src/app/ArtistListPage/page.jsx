'use client'
import React, { useContext } from 'react';
import { IconContext } from 'react-icons';
import ArtistPagination from './ArtistPagination';
import { useRouter } from 'next/navigation';
import styles from './ArtistListPage.module.scss'
import getCurrentUser from '../actions/getCurrentUser';

const ProductContext = React.createContext();

async function ArtistListPage() {
  const router = useRouter();
  
  const artist = {} // await axios.get(`${backend_url}/items`);
  const currentUser = getCurrentUser();
  // const { currentUser, product } = useContext(ProductContext);

    return (
    <ProductContext.Provider value={{ artist, currentUser }}>
      <IconContext.Provider value={{ color: '#fff' }}>
        <div className={styles.container} style={{ marginTop: '3vh' }}>

          <ArtistPagination PageLink={router}/>

        </div>
      </IconContext.Provider>
    </ProductContext.Provider>
    );
};

export default ArtistListPage;