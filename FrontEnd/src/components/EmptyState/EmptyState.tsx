'use client'

import React from 'react'
import Heading from '@/components/Heading/Heading';
import { useRouter } from 'next/navigation';
import styles from './EmptyState.module.scss'

interface EmptyStateProps {
    title?: string;
    subtitle?: string;
    showReset?: boolean;
}

const EmptyState = ({
    title = '상품이 없습니다.',
    subtitle = '상품을 등록하십시오.',
    showReset
}:EmptyStateProps) => {

    const router = useRouter();
  return (
    <div
        className={styles.my_container}
    >
        <Heading 
            center
            title={title}
            subtitle={subtitle}
        />
    </div>
  )
}

export default EmptyState