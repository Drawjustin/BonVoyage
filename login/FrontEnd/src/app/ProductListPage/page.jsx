import React from 'react';
import dynamic from 'next/dynamic';
import { Provider } from 'react-redux';
import store from '@/store';
import Categories from '@/components/categories/Categories/Categories';

const ProductListPage = () => {
  return (
    <div className='page'>
      <div className='container'>
        <h1>Products</h1>
            <Categories />
      </div>
    </div>
  );
};

export default ProductListPage;