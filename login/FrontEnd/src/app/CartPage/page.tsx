'use client'
import React from 'react'
import CartDetail from './cartDetail/CartDetail';
import { Provider } from 'react-redux';
import store from '@/store'

const CartPage = () => {

  return (
        <div className='page'>
          <Provider store={store}>
            <CartDetail/>
          </Provider>
        </div >
    
  )
}

export default CartPage