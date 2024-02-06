import React from 'react';
import dynamic from 'next/dynamic';
import { Provider } from 'react-redux';
import store from '@/store';
import Categories from '@/components/categories/Categories/Categories';
import axios from 'axios';
import Navbar from '@/components/Navbar/Navbar';

export default async function ProductListPage () {

  // try{
  //   const response = await axios.get('https://i10a207.p.ssafy.io/api/item');
  //   console.log(response.data);
  // } catch (error) {
  //   console.log("에러", error);
  // }
  
  

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