'use client'
import React from 'react';
import ProductPagination from './ProductPagination';
import { useRouter } from 'next/navigation';
import Navbar from '@/components/Navbar/Navbar';
import styles from './ProductListPage.module.scss'
import FloatingButton from '@/components/FloatingButton/FloatingButton'

const ProductListPage = () => {
  const router = useRouter();
    return (
      
      <div className={styles.container}>
        <Navbar />

                <ProductPagination PageLink={router}/>
              

            
        <FloatingButton href="/ProductUploadPage">+</FloatingButton>
      </div>
    );
};

export default ProductListPage;