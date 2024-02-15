'use client'
// import { useNavigation } from '@lucasmogari/react-pagination';
import React, { useState } from 'react';
import styles from './ReviewUploadPage.module.scss';
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
import { useSession } from 'next-auth/react';
import { display } from '@mui/system';

const ReviewUploadPage = () => {

  const [isArtist, setIsArtist] = useState(false);
  const navigate = useRouter();
  const [isLoading, setIsLoading] = useState(false);
  const currentUser = getCurrentUser();
  const [showAlert, setShowAlert] = useState(false);

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
      itemName: '',
      artist: '',
      member: '',
      title: '',
      description: '',
      imageSrc: '',
    }
  });

  const imageSrc = watch('imageSrc');
  const category = watch('category');

  const onSubmit: SubmitHandler<FieldValues> = async (data) => {
    setIsLoading(true);

    const now = dayjs();

    const backendUrl = 'https://i10a207.p.ssafy.io/api/review'

    try {
      if (isArtist) {
        const  ReviewUploadData = {
          // "memberId": data.member,
          "itemSeq" : '123',
          "memberId": 'hhh',
          "title": data.title,
          "content": data.description,
          // "artistId": data.artist,
          "artistId": 'hhh',
        }
        
        const response = await axios.post(`${backendUrl}/new`, ReviewUploadData, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          },
        }).then(response => {
          
          if (response.data !== 'fail') {
            console.log('success :', response.data);
            navigate.push('/ReviewPage');
          }
          else {
            console.log('fail :', response.data);
            setShowAlert(true);
          }
        });
      } 
      else {
        const ReviewUploadData = {
          // "memberId": data.member,
          "memberId": 'hhh',
          "itemSeq" : '123',
          "title": data.title,
          "content": data.description,
          // "artistId": data.artist,
          "artistId": 'hhh',

        }
        
        const response = await axios.post(`${backendUrl}/new`, ReviewUploadData, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          },
        }).then(response => {

          if (response.data !== '실패') {
            console.log('성공 :', response.data);
            navigate.push('/ReviewPage');
          }
          else {
            console.log('실패 :', response.data);
            setShowAlert(true);
          }
        })
        .catch(error => {
          console.error(ReviewUploadData);
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
      <div className={styles.productUploadContainer} style={{ display: 'flex', justifyContent: 'space-between', marginTop: '5vh' }}>
        <form className={styles.formContainer} onSubmit={handleSubmit(onSubmit)} style={{ marginTop: '2vh' }}>
          <div className={styles.heading}>
            <h1 style={{ color: '#f1efee' }}>리뷰 작성</h1>
            <div style={{ color: '#f1efee' }}>구매한 작품의 리뷰를 작성해주세요!</div>
          </div>

          <div className={styles.imageUploadContainer} style={{ marginTop: '3vh' }}>
            <ImageUpload onChange={(value) => setCustomValue('imageSrc', value)} value={imageSrc} />
          </div>

        


        <div style={{ width: '500px', marginTop: '12vh', marginLeft: '1vh' }}>
          <div style={{ alignContent: 'center'}}>
            <div style={{ marginBottom: '10px' }}>작품명</div>
            <div style={{ width: '30%', padding: '10px', color: 'black', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5', backgroundColor: 'lightgray' }}>{watch('itemSeq')}</div>
          </div>


          <div>
            <div style={{ marginBottom: '10px' }}>제목</div>
            <input
              id="title" 
              placeholder="제목을 입력하세요."
              disabled={isLoading || Object.keys(errors).length > 0}
              {...register('title', { required: true })} 
              style={{ width: '80%', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5' }}/>          
          </div>

            <div>
              <div style={{ marginBottom: '10px' }}>내용</div>
              <textarea
                id="description" 
                placeholder="작품 구매 후기를 적어주세요."
                disabled={isLoading || Object.keys(errors).length > 0}
                {...register('description', { required: true })}
                style={{ width: '80%', minHeight: '100px', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5' }} />
            </div>
            <div>
              <button className={styles.btn}>리뷰 등록</button>
            </div>
        </div>
        </form>
      </div>
    </Container>
  );
};

export default ReviewUploadPage;
