import React from 'react';
import dynamic from 'next/dynamic';
import { Provider } from 'react-redux';
import store from '@/store';
import Categories from '@/components/categories/Categories/Categories';
import axios from 'axios';
import Navbar from '@/components/Navbar/Navbar';
import EmptyState from '@/components/EmptyState';
import styles from './ProductListPage.module.scss'

export default async function ProductListPage () {

  
    const products = {} // await axios.get('https://i10a207.p.ssafy.io/api/item');
    // console.log(products.data);

  return (
    <div className='page'>
      <Navbar />
      <div className='container'>
        <h1  style={{ color: '#f1efee'}}>Products</h1>
            <Categories />

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
    </div>
  );
};