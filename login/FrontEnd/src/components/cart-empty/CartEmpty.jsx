import React from 'react'
import Link from 'next/link';
import styles from './CartEmpty.module.scss';

const CartEmpty= ({ title }) => {
  return (
    <div className={styles.cart_empty}>
      <h1 style={{ color: '#f1efee' }}>{title}가 비어있습니다.</h1>
      <p style={{ color: '#f1efee' }}>{title}에 상품을 넣어주세요.</p>
      <Link href="/"  style={{ color: '#f1efee' }}>계속 쇼핑하기</Link>
    </div>
  )
}

export default CartEmpty