'use client'
import React, { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import Loader from '../../components/loader/Loader';
import { useDispatch, useSelector } from '../../hooks/redux';
import { addToCart } from '../../store/cart/cart.slice';
import { fetchProduct } from '../../store/products/product.slice';
import styles from './ProductDetailPage.module.scss';
import Navbar from '../../components/Navbar/Navbar';
import getCurrentUser from '@/app/actions/getCurrentUser';
import Link from 'next/link';

const ProductDetailPage = () => {
//   const currentUser = getCurrentUser();
//   const { id } = useParams();
//   const productId = Number(id);
//   const dispatch = useDispatch(); -> 여기서 에러남 **********

//   const { product, isLoading } = useSelector((state) => state.productSlice);
//   const { products } = useSelector((state) => state.cartSlice);
//   const productMatching = products.some((product) => product.id === product.id);

//   useEffect(() => {

//     dispatch(fetchProduct(productId));

//   }, [dispatch, productId])

//   const addItemToCart = () => {
//     dispatch(addToCart(product));
//   }


//   return (
//     <div className='page'>
//       {isLoading ? (
//         <Loader />
//       ) :
//         <div className={styles.card_wrapper}>
//           <div className={styles.card_img}>
//             <img src={product.image} alt="product card" />
//           </div>
//           <div className={styles.card_description}>
//             <h3>{product.category}</h3>
//             <h1>{product.title}</h1>

//             <h4>$ {product.price}</h4>
//             <p>{product.description}</p>
//             <div>
//               <button
//                 disabled={productMatching}
//                 onClick={() => !productMatching && addItemToCart()}
//               >
//                 {productMatching ? "장바구니에 담긴 제품" : "장바구니에 담기"}
//               </button>
//               <Link href="/cart">장바구니로 이동</Link>
//             </div>
//           </div>
//         </div>
//       }

//     </div >
//   )
}

export default ProductDetailPage