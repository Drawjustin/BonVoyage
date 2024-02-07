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
import { formatTime } from '@/helpers/dayjs';
import dayjs from 'dayjs';
import Navbar from '@/components/Navbar/Navbar';
import getCurrentUser from '@/app/actions/getCurrentUser';
// import './ProductUploadPage.sass';

const ProductUploadPage = () => {
  const router = useRouter();
  const [isLoading, setIsLoading] = useState(false);
  const currentUser = getCurrentUser();

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
      price: '(원)'
    }
  });

  const imageSrc = watch('imageSrc');
  const category = watch('category');

  const onSubmit: SubmitHandler<FieldValues> = (data) => {
    setIsLoading(true);

    const now = dayjs();

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

    axios.post('https://i10a207.p.ssafy.io/api/item/new', data)
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
      <Navbar/>
      <div className={styles.productUploadContainer}>
        {/* <form>
              <h1>일반 판매 작품 등록</h1>
              <div className="question">
                <input type="text" required />
                <label>작품명</label>
              </div>
              <div className="question">
                <input type="text" required />
                <label>상세 설명</label>
              </div>
              <div className="question">
                <input type="text" required />
                <label>규격</label>
              </div>
              <div className="question">
                <input type="text" required />
                <label>가격</label>
              </div>
              <button>등록하기</button>
        </form> */}
        
        <form className={styles.formContainer} onSubmit={handleSubmit(onSubmit)}>
          <div className={styles.heading}>
            <h1 style={{ color: '#f1efee' }}>일반 판매 작품 등록</h1>
            <div style={{ color: '#f1efee' }}>일반 판매할 작품의 정보를 등록해주세요!</div>
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
