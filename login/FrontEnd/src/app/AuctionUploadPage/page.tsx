'use client'
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
import dayjs from 'dayjs';
import Navbar from '@/components/Navbar/Navbar';

const AuctionUploadPage = () => {

    const [selectedDate, setSelectedDate] = useState('');
  
    const handleDateChange = (event:any) => {
      const inputDate = event.target.value;
      // dayjs를 사용하여 입력받은 날짜를 처리
      const formattedDate = dayjs(inputDate).format('YYYY-MM-DD');
      setSelectedDate(formattedDate);
    };

  const router = useRouter();
  const [isLoading, setIsLoading] = useState(false);

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
      category: 'auction',
      imageSrc: '',
      size: '',
      date: '',
      price: '(원)'
    }
  });

  const imageSrc = watch('imageSrc');
  const category = watch('category');

  const onSubmit: SubmitHandler<FieldValues> = (data) => {
    setIsLoading(true);

    data = {
        itemSeq:12,
        itemName:data.title,
        itemWidth:230,
        itemHeight:460,
        itemLike:0,
        itemSe1lPrice:data.price,
        itemIsSold:false,
        itemCreatedDate: dayjs()
    }

    axios.post('https://i10a207.p.ssafy.io/api/auction/new', data)
      .then(response => {
        // router.push(`/products/${response.data.id}`);
        console.log('성공', response.data);
      })
      .catch((err) => {
        console.error(err);
      })
      .finally(() => {
        setIsLoading(false);
      });

      console.log(data);
  };

  const setCustomValue = (id: string, value: any) => {
    setValue(id, value);
  };

  return (
    <Container>
      <Navbar />
      <div className={styles.productUploadContainer}>
        <form className={styles.formContainer} onSubmit={handleSubmit(onSubmit)}>
          <div className={styles.heading}>
            <h1 style={{ color: '#f1efee' }}>경매 작품 등록</h1>
            <div style={{ color: '#f1efee' }}>경매에 참가할 작품의 정보를 등록해주세요!</div>
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
            <div>규격</div>
            <input 
              id="size" 
              placeholder="예) 가로 * 세로 53.0 * 40.9 cm"
              disabled={isLoading || Object.keys(errors).length > 0}
              {...register('size', { required: true })} 
              style={{ width: '100%', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5' }}/>
          </div>

          <div>
            <div>경매 일정</div>
            <input
              id="datepicker"
              type="date"
              value={selectedDate}
              // onChange={handleDateChange}
              // placeholder="경매 날짜를 선택하세요."
              disabled={isLoading || Object.keys(errors).length > 0}
              {...register('date', { required: true })}
              // 필요한 경우 추가 속성이나 이벤트 핸들러를 추가합니다
              style={{ width: '100%', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5' }}
              /> 
              {selectedDate && (
              <p>선택한 날짜: {selectedDate}</p>
            )}
          </div>

          <div>
            <div>경매 시작가</div>
            <input
              id="price"
              placeholder="경매 시작가를 입력하세요."
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

export default AuctionUploadPage;
