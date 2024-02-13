'use client'
// import { useNavigation } from '@lucasmogari/react-pagination';
import React, { useState } from 'react';
import styles from './ProductUploadPage.module.scss';
import Input from '@/components/Input/Input';
import { FieldValues, SubmitHandler, useForm } from 'react-hook-form';
import Button from '@/components/Button';
import Container from '@/components/Container/Container';
import Heading from '@/components/Heading/Heading';
import ImageUpload from '@/components/ImageUpload/ImageUpload';
import axios from 'axios';
import { useRouter } from 'next/navigation';
import { formatTime } from '@/helpers/dayjs';
import dayjs from 'dayjs';
import Navbar from '@/components/Navbar/Navbar';
import getCurrentUser from '../actions/getCurrentUser';
// import './ProductUploadPage.sass';

const ProductUploadPage = () => {

  const [isArtist, setIsArtist] = useState(true);
  const navigate = useRouter();
  const [isLoading, setIsLoading] = useState(false);
  const currentUser = getCurrentUser();
  const [showAlert, setShowAlert] = useState(false);

  const [width, setWidth] = useState('');
  const [height, setHeight] = useState('');

  const handleInputChange = (event: any, setValueFuction: any) => {
    setValueFuction(event.target.value);
  };

  const handleHeightChange = (event: any) => {
    setHeight(event.target.value);
  };

  const {
    register,
    handleSubmit,
    setValue,
    watch,
    formState: {
      errors,
    },
    reset
  } = useForm<FieldValues>({
    defaultValues: {
      title: '',
      description: '',
      width: '',
      height: '',
      price: '',
      category: 'item',
      imageSrc: '',
    }
  });

  const imageSrc = watch('imageSrc');
  const category = watch('category');

  const onSubmit: SubmitHandler<FieldValues> = async (data) => {
    setIsLoading(true);

    const now = dayjs();

    const backendUrl = 'https://i10a207.p.ssafy.io/api/item'

    try {
      if (isArtist) {
        const ProductUploadData = {
          "itemName": data.title,
          "explain" : data.description,
          "itemWidth": data.width,
          "itemHeight": data.height,
          // "itemLike": 0,
          "itemSellPrice": data.price,
          // "itemIsSold": false,
          "artistId": "dfdff0"
        }
  
        const response = await axios.post(`${backendUrl}/new`, ProductUploadData, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          },
        }).then(response => {

          if (response.data !== 'fail') {
            console.log('success :', response.data);
            navigate.push('/');
          }
          else {
            console.log('fail :', response.data);
            setShowAlert(true);
          }
        });
        // .catch(error => {
        //   console.error('error', error.response ? error.response.data : error.message);
        //   // 로그인 실패하면 팝업 표시할 것
          
        // });
  
        // if (response.data !== '바보 멍텅구리 실패했잔요') {
        //   console.log('성공 :', response.data);
        //   navigate.push('/ProductListPage');
        // } else {
        //   console.log('실패 :', response.data);
        //   setShowAlert(true);
        // }
      } 
      else {
        const ProductUploadData = {
          "itemName": data.title,
          "explain" : data.description,
          "itemWidth": data.width,
          "itemHeight": data.height,
          // "itemLike": 0,
          "itemSellPrice": data.price,
          // "itemIsSold": false,
          "artistId": "dfdff0"
        }
        
        const response = await axios.post(`${backendUrl}/new`, ProductUploadData, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          },
        }).then(response => {

          if (response.data !== '실패') {
            console.log('성공 :', response.data);
            navigate.push('/ProductListPage');
          }
          else {
            console.log('실패 :', response.data);
            setShowAlert(true);
          }
        })
        .catch(error => {
          console.error(ProductUploadData);
          console.error('에러', error.response ? error.response.data : error.message);
          // 로그인 실패하면 팝업 표시할 것
          
        });
  
        // if (response.data !== '바보 실패했잔요') {
        //   console.log(' 성공 :', response.data);
        //   navigate.push('/ProductListPage');
        // } else {
        //   console.log('실패 :', response.data);
        //   setShowAlert(true);
        // }
      }
    // } catch (error:any) {
    //   console.error('에러 발생:', error.response ? error.response.data : error.message);
    //   // 에러 처리 로직 추가
    } finally {
      setIsLoading(false);
    }
  };

    const setCustomValue = (id: string, value: any) => {
      setValue(id, value);
    };
    
    return (
      <Container>
      <div className={styles.productUploadContainer}>
        <form className={styles.formContainer} onSubmit={handleSubmit(onSubmit)}>
          <div className={styles.heading}>
            <h1 style={{ color: '#f1efee' }}>일반 판매 작품 등록</h1>
            <div style={{ color: '#f1efee',  }}>일반 판매할 작품의 정보를 등록해주세요!</div>
          </div>

          <div className={styles.imageUploadContainer}>
            <ImageUpload onChange={(value) => setCustomValue('imageSrc', value)} value={imageSrc} />
          </div>

          <div>
            <div>작품명</div>
            <input
              id="title" 
              placeholder="작품명을 입력하세요."
              disabled={isLoading || Object.keys(errors).length > 0}
              {...register('title', { required: true })} 
              style={{ width: '100%', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5' }}/>          
          </div>

          <div>
            <div>설명</div>
            <input
              id="description" 
              placeholder="작품 설명을 입력하세요."
              disabled={isLoading || Object.keys(errors).length > 0}
              {...register('description', { required: true })}
              style={{ width: '100%', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5' }} />
          </div>

          <div>
            <div>가로</div>
            <input 
              id="widthInput" 
              placeholder="cm 기준의 규격을 입력해주세요."
              disabled={isLoading || Object.keys(errors).length > 0}
              {...register('size', { required: true })} 
              style={{ width: '100%', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5' }}/>
         
            <div>세로</div>
            <input 
              id="heightInput" 
              placeholder="cm 기준의 규격을 입력해주세요."
              disabled={isLoading || Object.keys(errors).length > 0}
              {...register('size', { required: true })} 
              style={{ width: '100%', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5' }}/>
          </div>

          <div>
            <div>가격</div>
            <input
              id="price"
              placeholder="가격을 입력하세요."
              disabled={isLoading || Object.keys(errors).length > 0}
              {...register('price', { required: true })}
              // 필요한 경우 추가 속성이나 이벤트 핸들러를 추가합니다
              style={{ width: '100%', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5' }}
              /> 
          </div>
          <div>
            <button className={styles.btn}>작품 등록</button>
          </div>
        </form>

</div>

    </Container>
  );
};

export default ProductUploadPage;
