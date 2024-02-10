'use client';
import Container from '@/components/Container/Container';
import ProductHead from '@/components/products/ProductHead/ProductHead';
import ProductInfo from '@/components/products/ProductInfo/ProductInfo';
import { useRouter } from 'next/navigation';
import React from 'react';
import styles from './ProductClient.module.scss';

const ProductClient = ({ product, currentUser }: any) => {
  const router = useRouter();

  return (
    <div className={styles['product-client-container']}>
      <Container>
        <div className={styles['max-w-screen-lg']}>
          <div className={styles['flex']}>
            <ProductHead
              title={product.title}
              imageSrc={product.imageSrc}
              id={product.id}
              currentUser={currentUser}
            />
            <div className={styles['grid']}>
              <ProductInfo
                user={product.user}
                createdAt={product.createdAt}
                description={product.description}
              />
            </div>
          </div>
        </div>
      </Container>
    </div>
  );
};

export default ProductClient;