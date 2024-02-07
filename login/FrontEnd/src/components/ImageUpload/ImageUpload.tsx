'use client'
import { CldUploadWidget } from 'next-cloudinary';
import React from 'react';
import { TbPhotoPlus } from 'react-icons/tb';
import Image from 'next/image';
import styles from './ImageUpload.module.scss'; // Import the module.scss file

interface ImageUploadProps {
  onChange: (value: string) => void;
  value: string;
}

const ImageUpload = ({ onChange, value }: ImageUploadProps) => {
  const handleUpload = (result: any) => {
    onChange(result.info.secure_url);
  };

  const uploadPreset = process.env.NEXT_PUBLIC_CLOUDINARY_UPLOAD_PRESET;

  return (
    <CldUploadWidget
      onUpload={handleUpload}
      uploadPreset={uploadPreset}
      options={{
        maxFiles: 3,
      }}
    >
      {({ open }) => (
        <div onClick={() => open?.()} className={styles.uploadContainer}>
          <TbPhotoPlus size={50} className={styles.icon} />
          {value && (
            <div className={styles.imagePreview}>
              <Image src={value} alt="" width={210} height={210} />
            </div>
          )}
        </div>
      )}
    </CldUploadWidget>
  );
};

export default ImageUpload;
