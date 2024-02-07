'use client'
import React from 'react';
import ProductPagination from './ProductPagination';
import { useRouter } from 'next/navigation';
import Navbar from '@/components/Navbar/Navbar';
import styles from './ProductListPage.module.scss'
import FloatingButton from '@/components/FloatingButton/FloatingButton'
import { Routes, Route } from 'react-router-dom';

const ProductListPage = () => {
  const router = useRouter();
    return (
      
      <div className={styles.container}>
        <Navbar />
                  
                        <Routes>
                            <Route path={router} element={<ProductPagination/>} />
                        </Routes>
            
        <FloatingButton href="/ProductUploadPage">+</FloatingButton>
      </div>
    );
};

export default ProductListPage;