import React from 'react';
import dynamic from 'next/dynamic';
import { Provider } from 'react-redux';
import store from '@/store';
import Categories from '@/components/categories/Categories/Categories';
import Navbar from '../../components/Navbar/Navbar';

const ProductListPage = () => {
  return (
    <div className='page'>
      <Navbar />
      <div className='container'>
        <h1  style={{ color: '#f1efee'}}>Products</h1>
            <Categories />
      </div>
    </div>
  );
};

export default ProductListPage;