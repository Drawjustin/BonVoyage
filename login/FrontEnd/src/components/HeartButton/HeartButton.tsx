//import useFavorite from '@/hooks/useFavorite';
'use client'
import React, { useState } from 'react';
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai';
import styles from './HeartButton.module.scss';

interface HeartButtonProps {
    productId: string;
    currentUser?: boolean | null;
}


const HeartButton = ({ productId, currentUser }: HeartButtonProps) => {

    const [isFavorited, setIsFavorited] = useState(false);


    const toggleFavorite = (event: React.MouseEvent) => {
      event.stopPropagation();
      setIsFavorited(!isFavorited);
      console.log('클릭');
    }
    // const hasFavorite = false;

    return (
        <div
          onClick={toggleFavorite}
          className={styles.heartButton}
        >
          {isFavorited ? (
            <AiFillHeart
              size={24}
              className={styles.heartButton__filled}
            />

          ) : (

            <AiOutlineHeart
              size={24}
              className={styles.heartButton__outline}
            />
          )
        
        }
    
        </div>
      );
    };

export default HeartButton