import React from 'react';
import dynamic from 'next/dynamic';
import { Provider } from 'react-redux';
import axios from 'axios';
import Navbar from '@/components/Navbar/Navbar';
import EmptyState from '@/components/EmptyState/EmptyState';
import styles from './ProductListPage.module.scss'
import FloatingButton from '@/components/FloatingButton/FloatingButton'
import getCurrentUser from '@/app/actions/getCurrentUser';

export default async function ProductListPage () {
  
  const currentUser = await getCurrentUser();

  try {
    const response = await axios.get('https://i10a207.p.ssafy.io/api/item');
    const products = response.data;

    return (
      <div className="page">
        <Navbar />
        <div className="container" style={{ marginTop: '10px' }}>
          <h1 style={{ color: '#f1efee', textAlign: 'left' }}>Products</h1>

          {Array.isArray(products) && products.length > 0 ? (
            <div className={styles.grid}>
              {products.map((product) => (
                <ProductCard
                  currentUser={'퍄퍄퍄'}
                  key={product.itemSeq}
                  data={product}
                />
              ))}
            </div>
          ) : (
            <EmptyState showReset />
          )}
        </div>
        <FloatingButton href="/ProductUploadPage">+</FloatingButton>
      </div>
    );
  } catch (error) {
    console.error('Error fetching products:', error);
    // Handle the error as needed
    return (
      <div className="page">
        <Navbar />
        <div className="container" style={{ marginTop: '10px' }}>
          <h1 style={{ color: '#f1efee', textAlign: 'left' }}>Products</h1>
          <EmptyState showReset />
        </div>
        <FloatingButton href="/ProductUploadPage">+</FloatingButton>
      </div>
    );
  }
};