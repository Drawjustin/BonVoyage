import React from 'react';
import dynamic from 'next/dynamic';
import { Provider } from 'react-redux';
import axios from 'axios';
import Navbar from '@/components/Navbar/Navbar';
import EmptyState from '@/components/EmptyState';
import styles from './ProductListPage.module.scss'
import FloatingButton from '@/components/FloatingButton/FloatingButton'

export default async function ProductListPage () {

  
    const products = {} // await axios.get('https://i10a207.p.ssafy.io/api/item');
    // console.log(products.data);

  return (
    <div className='page'>
      <Navbar />
      <div className='container' style={{ marginTop: '10px' }}>
        <h1  style={{ color: '#f1efee', textAlign: 'left'}}>Products</h1>

            {
              products.data?.length === 0
              ?
              <EmptyState showReset />
              :
              <>
              <div className={styles.grid}>
              {products.data?.map((product) =>
                  <ProductCard
                    currentUser={'퍄퍄퍄'}
                    key={product.itemSeq}
                    data={product}
                  />)}
              </div>
              </>
            }
      </div>
      <FloatingButton
      href="/ProductUploadPage">
        +
      </FloatingButton>
    </div>
  );
};