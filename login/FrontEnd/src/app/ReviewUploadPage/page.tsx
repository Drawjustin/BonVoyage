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

    const backendUrl = 'https://i10a207.p.ssafy.io/api/Review'

    try {
      if (isArtist) {
        const ReviewUploadData = {
          "itemName": "우주먼지",
          "reviewContent": '',
          "reviewVisit": 0,
          "reviewCreatedDate": dayjs().format('YY-MM-DD'),
          "member": {
            "memberSeq": 1
          }
        }
        
        const response = await axios.post(`${backendUrl}`, ReviewUploadData, {
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
        })
        .catch(error => {
          console.error('error', error.response ? error.response.data : error.message);
          // 로그인 실패하면 팝업 표시할 것
          
        });

      } 
      else {
        const ReviewUploadData = {
            "itemName": "우주먼지",
            "reviewContent": '',
            "reviewVisit": 0,
            "reviewCreatedDate": dayjs().format('YY-MM-DD'),
            "member": {
              "memberSeq": 1
        }
    }
        
        //const session = useSession();

        //console.log(session);
        // 작품명을 DB로부터 받는 것

        // const respond = axios.get(`${backendUrl}/api/members/ggg`).then(respond => {

        //     const subject = respond.data.orderDetail.item.itemName;
        //     console.log(subject)

        // });

            






        const response = await axios.post(`${backendUrl}/`, ReviewUploadData, {
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
          console.error(ReviewUploadData);
          console.error('에러', error.response ? error.response.data : error.message);
          // 로그인 실패하면 팝업 표시할 것
          
        });
  
      }
    } catch (error:any) {
      console.error('에러 발생:', error.response ? error.response.data : error.message);
      // 에러 처리 로직 추가
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
            <h1 style={{ color: '#f1efee' }}>리뷰 작성</h1>
            <div style={{ color: '#f1efee' }}>구매한 작품의 리뷰를 작성해주세요!</div>
          </div>

          <div className={styles.imageUploadContainer}>
            <ImageUpload onChange={(value) => setCustomValue('imageSrc', value)} value={imageSrc} />
          </div>

          <div>
            <div style={{ marginBottom: '10px' }}>작품명</div>
            <div style={{ width: '100%', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5', backgroundColor: 'lightgray' }}></div>
          </div>

          <div>
            <div style={{ marginBottom: '10px' }}>후기</div>
            <textarea
              id="description" 
              placeholder="작품 구매 후기를 적어주세요."
              disabled={isLoading || Object.keys(errors).length > 0}
              {...register('description', { required: true })}
              style={{ width: '100%', minHeight: '100px', padding: '10px', marginBottom: '15px', boxSizing: 'border-box', borderRadius: '8px', border: 'solid 1px #171de5' }} />
          </div>
          <div>
            <button className={styles.btn}>리뷰 등록</button>
          </div>
        </form>

</div>

    </Container>
  );
};

export default ReviewUploadPage;
