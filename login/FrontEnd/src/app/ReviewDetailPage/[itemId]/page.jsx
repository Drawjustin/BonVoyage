'use client'
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Loader from '@/components/loader/Loader';
import { useSelector } from 'react-redux';
import { useDispatch } from 'react-redux';
import styles from './ProductDetailPage.module.scss';
import { useRouter } from 'next/navigation';
import { ReviewList } from '@/constants';
import getCurrentUser from '@/app/actions/getCurrentUser';
import Link from 'next/link';
import axios from 'axios';

const ReviewDetailPage = (props) => {
  const currentUser = getCurrentUser();
  const { id } = useParams();
  const productId = Number(id);
  const dispatch = useDispatch();
  const history = useRouter();

  const handleDeleteClick = async () => {
    // 글 삭제 버튼 클릭 시 실행할 로직 추가
    // try {
    //   // 여기에서 삭제 요청을 보내고 성공 여부에 따라 다른 동작 수행
    const backendurl = 'https://i10a207.p.ssafy.io/api';
    await axios.delete(`${backendurl}/review/${props.params.itemId}`)
      .then( response => {
        console.log('삭제 성공', response.data);
      // 예시: 글 삭제 후 홈페이지로 이동
        history.push('/ReviewPage');
      })
     .catch (error => {
      console.error('Error deleting product:', error);
    }); 
  };


  // redux 상태에서 해당 상품 정보 불러오긔
  // const { product, isLoading } = useSelector((state) => state.productSlice);
  const [review, setReview] = useState(null);

  useEffect(() => {
    const fetchReview = async () => {
      try {
        const response = await axios.get(`https://i10a207.p.ssafy.io/api/review/${props.params.itemId}`);
        // console.log(props)
        console.log(response.data)
        setReview(response.data);
      } catch (error) {
        console.error('Erorr fetchingreview details:', error);
        // console.log(props)
      }
    };
    fetchReview()
  }, []);
    // redux에서 해당 상품 정보를 가져오는 액션 디스패치
  //   dispatch(fetchProduct(productId));
  // }, [dispatch, productId]);



  return (
    <div className='page'>
      {!review ? (
        <Loader />
      ) : (
        <div className={styles.card_wrapper}>
          <div className={styles.card_img}>
            <img src={ReviewList[(props.params.itemId)%5]} alt="review card" />
          </div>
          <div className={styles.card_description}>
            <h3>{review.title}</h3>
            <h2>{review.itemName} | {review.artistId} 작가</h2>
            <p>{review.content}</p>

            {
              currentUser ?
              <button style={{ padding: '10px' }} onClick={handleDeleteClick}>삭제</button>
              :
              <></>
            }
              
            </div>
          </div>

      )}

    </div >
  );
};

export default ReviewDetailPage