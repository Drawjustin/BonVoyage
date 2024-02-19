import getCurrentUser from '@/app/actions/getCurrentUser';
// import getProductById from '@/app/actions/getProductById'
import EmptyState from '@/components/EmptyState/EmptyState';
import React from 'react'
import ProductClient from './ProductClient';
import axios from 'axios';

interface Params {
  productId?: string
}

const ProductPage = async () => {
  

  const backend_url = 'https://i10a207.p.ssafy.io/api';

  const product = await axios.get(`${backend_url}/items`);
  const currentUser = await getCurrentUser();
  
  if(!product) {
    return (
      <EmptyState />
    )
  }

  return (
    <ProductClient
      product={product}
      currentUser={currentUser}
    />
  )
}

export default ProductPage