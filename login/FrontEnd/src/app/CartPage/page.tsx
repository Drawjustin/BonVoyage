'use client'
import React from 'react'
import CartDetail from './cartDetail/CartDetail';
import { Provider } from 'react-redux';
import store from '@/store';
import Navbar from '@/components/Navbar/Navbar';
import getCurrentUser from '@/app/actions/getCurrentUser';

const CartPage = () => {

  const currentUser = getCurrentUser();

  return (
        <div className='page'>
      <Navbar />
          <Provider store={store}>
            <CartDetail/>
          </Provider>
        </div >
    
  )
}

export default CartPage