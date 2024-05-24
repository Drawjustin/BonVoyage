import React from 'react';
import Link from 'next/link';
import styles from './FloatingButton.module.scss';

interface FloatingButtonProps {
  children: React.ReactNode;
  href: string;
}

export default function FloatingButton({ children, href }: FloatingButtonProps) {
  return (
    <Link href={href} className={styles.floatingButton}>
        {children}
    </Link>
  );
}
