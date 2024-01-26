'use client';
import React, {useState} from 'react'
import Input from '@/components/Input'
import { FieldValues, SubmitHandler, useForm } from 'react-hook-form';
import Button from '@/components/Button';
import Container from '@/components/Container';
import Heading from '@/components/Heading';
import ImageUpload from '@/components/ImageUpload';
import axios from 'axios';
import { useRouter } from 'next/navigation';

const ReviewUploadPage = () => {
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
            imageSrc: '',
        }
    })

    const imageSrc = watch('imageSrc');

    const onSubmit: SubmitHandler<FieldValues> = (data) => {
        setIsLoading(true);

        axios.post('/api/review', data)
            .then(response => {
                router.push(`/review/${response.data.id}`);
            })
            .catch((err) => {
                console.error(err);
            })
            .finally(() => {
                setIsLoading(false);
            })
    }

    const setCustomValue = (id: string, value: any) => {
        setValue(id, value);
    }

  return (
    <Container>
        <div
            className='max-w-screen-lg mx-auto'
        >
            <form
                className='flex flex-col gap-8'
                onSubmit={handleSubmit(onSubmit)}
                >
                
                <Heading
                    title="Review Upload"
                    subtitle="upload your Review"
                />

                <ImageUpload
                    onChange={(value) => setCustomValue('imageSrc', value)}
                    value={imageSrc}
                />

                <Input
                    id="title"
                    label="Title"
                    disabled={isLoading}
                    register={register}
                    errors={errors}
                    required
                />
                <hr />

                <Input
                    id="description"
                    label="Description"
                    disabled={isLoading}
                    register={register}
                    errors={errors}
                    required
                />

                <Button label="리뷰 생성하기" />
            </form>
            

            </div>
    </Container>
    
  )
}

export default ReviewUploadPage