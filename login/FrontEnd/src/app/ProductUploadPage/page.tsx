'use client'
import React, { useState } from 'react';
import styles from './ProductUploadPage.module.scss';
import Input from '@/components/Input/Input';
import { FieldValues, SubmitHandler, useForm } from 'react-hook-form';
import Button from '@/components/Button';
import Container from '@/components/Container/Container';
import Heading from '@/components/Heading/Heading';
import ImageUpload from '@/components/ImageUpload/ImageUpload';
import { categories } from '@/components/categories/Categories/Categories';
import CategoryInput from '@/components/categories/CategoryInput/CategoryInput';
import axios from 'axios';
import { useRouter } from 'next/navigation';
import { formatTime } from '@/helpers/dayjs';
import dayjs from 'dayjs';

const ProductUploadPage = () => {
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
      price: 1
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

    axios.post('https://i10a207.p.ssafy.io/api/item/', data)
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
      <div className={styles.productUploadContainer}>
        <form className={styles.formContainer} onSubmit={handleSubmit(onSubmit)}>
          <div className={styles.heading}>
            <Heading title="Product Upload" subtitle="upload your product" />
          </div>

          <div className={styles.imageUploadContainer}>
            <ImageUpload onChange={(value) => setCustomValue('imageSrc', value)} value={imageSrc} />
          </div>

          <Input id="title" label="Title" disabled={isLoading} register={register} errors={errors} required />
          <hr />

          <Input id="description" label="Description" disabled={isLoading} register={register} errors={errors} required />
          <hr />

          <Input id="price" label="Price" formatPrice disabled={isLoading} register={register} errors={errors} required />
          <hr />

          <div className={styles.categoryGrid}>
            {categories.map((item) => (
              <div key={item.label} className={styles.categoryItem}>
                <CategoryInput
                  onClick={(category) => setCustomValue('category', category)}
                  selected={category === item.path}
                  label={item.label}
                  icon={item.icon}
                  path={item.path}
                />
              </div>
            ))}
          </div>

          <hr />
          
          <div className={styles.button}>
            <Button label="상품 생성하기" />
          </div>
        </form>
      </div>
    </Container>
  );
};

export default ProductUploadPage;
