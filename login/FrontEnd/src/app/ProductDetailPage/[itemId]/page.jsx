'use client'
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Loader from '@/components/loader/Loader';
import { useSelector } from 'react-redux';
import { useDispatch } from 'react-redux';
import { addToCart } from '@/store/cart/cart.slice';
import { fetchProduct } from '@/store/products/product.slice';
import styles from './ProductDetailPage.module.scss';
import { useRouter } from 'next/navigation';
import { ImageList } from '@/constants';
import getCurrentUser from '@/app/actions/getCurrentUser';
import Link from 'next/link';
import axios from 'axios';

const ProductDetailPage = (props) => {
  // const currentUser = getCurrentUser();
  const { id } = useParams();
  const productId = Number(id);
  const dispatch = useDispatch();
  const history = useRouter();


  const handleDeleteClick = async () => {
    // 글 삭제 버튼 클릭 시 실행할 로직 추가
    // try {
    //   // 여기에서 삭제 요청을 보내고 성공 여부에 따라 다른 동작 수행
    const backendurl = 'https://i10a207.p.ssafy.io/api';
    await axios.delete(`${backendurl}/item/${props.params.itemId}`)
      .then( response => {
        console.log('삭제 성공', response.data);
      // 예시: 글 삭제 후 홈페이지로 이동
        history.push('/ProductListPage');
      })
     .catch (error => {
      console.error('Error deleting product:', error);
    }); 
  };

  const handleEditClick = () => {
    // 글 수정 버튼 클릭 시 실행할 로직 추가
    // 예시: 글 수정 페이지로 이동
    history.push(`/ProductDetailModifyPage/${props.params.itemId}`);
  };

  // redux 상태에서 해당 상품 정보 불러오긔
  // const { product, isLoading } = useSelector((state) => state.productSlice);
  const [product, setProduct] = useState(null);
  const { products } = useSelector((state) => state.cartSlice);
  const productMatching = products.some((product) => product.id === product.id);
  const [artist, setArtist] = useState('');

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await axios.get(`https://i10a207.p.ssafy.io/api/item/${props.params.itemId}`);
        // console.log(props)
        console.log(response.data)
        setProduct(response.data);

        const response_artist = await axios.get(`https://i10a207.p.ssafy.io/api/artists/${response.data.artistId}`);
        // console.log(props)
        console.log(response_artist.data)
        setArtist(response_artist.data);
      } catch (error) {
        console.error('Erorr fetchingproduct details:', error);
        // console.log(props)
      }
    };

    fetchProduct();
  }, []);
    // redux에서 해당 상품 정보를 가져오는 액션 디스패치
  //   dispatch(fetchProduct(productId));
  // }, [dispatch, productId]);

  const addItemToCart = () => {
    dispatch(addToCart(product));
  };


  return (
    <div className='page'>
      {!product ? (
        <Loader />
      ) : (
        <div className={styles.card_wrapper}>
          <div className={styles.card_img}>
            <img src={ImageList[(props.params.itemId)%15]} alt="product card" />
          </div>
          <div className={styles.card_description}>
            <h3>{product.itemName}</h3>
            {
              artist.nickName === '쌉가능' ? 
              <h2>닉네임 : 아린 작가</h2>
              :
              <h2>닉네임 : {artist.nickName} 작가</h2>
            }
            

            <p>{product.itemSellPrice}  (원) </p>
            <p>{product.explain}</p>
            <p>{product.itemWidth} * {product.itemHeight}  (cm)</p>
            <div style={{  display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
              <button
                style={{ padding: 0, marginLeft: 0 }}
                disabled={productMatching}
                onClick={() => !productMatching && addItemToCart()}
              >
                {productMatching ? "장바구니에 담긴 제품" : "장바구니에 담기"}
              </button>
              <Link href="/cart" style={{ padding: 0 }}>장바구니로 이동</Link>
              {/* 수정 버튼 */}
              <button style={{ padding: '10px',  }} onClick={handleEditClick}>수정</button>
              {/* 삭제 버튼 */}
              <button style={{ padding: '10px' }} onClick={handleDeleteClick}>삭제</button>
            </div>
          </div>
        </div>
      )}

    </div >
  );
};

export default ProductDetailPage