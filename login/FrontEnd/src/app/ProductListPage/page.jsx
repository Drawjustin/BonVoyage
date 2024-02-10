'use client'
import React from 'react';
import ProductPagination from './ProductPagination';
import { useRouter } from 'next/navigation';
import Navbar from '@/components/Navbar/Navbar';
import styles from './ProductListPage.module.scss'
import FloatingButton from '@/components/FloatingButton/FloatingButton'
import axios from 'axios';
import getCurrentUser from '../actions/getCurrentUser';

async function ProductListPage() {
  const router = useRouter();
  
  const backend_url = 'https://i10a207.p.ssafy.io/api';
  const product = {} // await axios.get(`${backend_url}/items`);
  const currentUser = await getCurrentUser();

    return (
      
      <div className={styles.container}>
        <Navbar />
                <ProductPagination PageLink={router}/>
            
        <FloatingButton href="/ProductUploadPage">+</FloatingButton>
      </div>
    );
};

export default ProductListPage;