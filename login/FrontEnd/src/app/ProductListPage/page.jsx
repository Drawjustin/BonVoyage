'use client'
import React from 'react';
import { IconContext } from 'react-icons';
import ProductPagination from './ProductPagination';
import { useRouter } from 'next/navigation';
import styles from './ProductListPage.module.scss'
import FloatingButton from '@/components/FloatingButton/FloatingButton'
import axios from 'axios';
import getCurrentUser from '../actions/getCurrentUser';

const ProductContext = React.createContext();

const ProductListPage = () =>{
  const router = useRouter();
  
  const backend_url = 'https://i10a207.p.ssafy.io/api/item';
  const product = {} // await axios.get(`${backend_url}/items`);
  const currentUser = getCurrentUser();
  // const { currentUser, product } = useContext(ProductContext);

    return (
    <ProductContext.Provider value={{ product, currentUser }}>
      <IconContext.Provider value={{ color: '#fff' }}>
        <div className={styles.container} style={{ marginTop: '3vh' }}>

          <ProductPagination PageLink={router}/>
              
          {
          currentUser !== null ?
          <FloatingButton href="/ProductUploadPage">+</FloatingButton>
          :
          <></>
        }

        </div>
      </IconContext.Provider>
    </ProductContext.Provider>
    );
};

export default ProductListPage;