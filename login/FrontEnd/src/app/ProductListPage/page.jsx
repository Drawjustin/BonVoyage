'use client'
import React, { useContext } from 'react';
import { IconContext } from 'react-icons';
import ProductPagination from './ProductPagination';
import { useRouter } from 'next/navigation';
import styles from './ProductListPage.module.scss'
import FloatingButton from '@/components/FloatingButton/FloatingButton'
import axios from 'axios';
import getCurrentUser from '../actions/getCurrentUser';

const ProductContext = React.createContext();

async function ProductListPage() {
  const router = useRouter();
  
  const backend_url = 'https://i10a207.p.ssafy.io/api';
  const product = {} // await axios.get(`${backend_url}/items`);
  const currentUser = await getCurrentUser();

    return (
    <IconContext.Provider value={{ color: '#fff' }}>
      <div className={styles.container} style={{ marginTop: '3vh' }}>

        <ProductPagination PageLink={router}/>
            
        <FloatingButton href="/ProductUploadPage">+</FloatingButton>
      </div>
    </IconContext.Provider>
    );
};

export default ProductListPage;