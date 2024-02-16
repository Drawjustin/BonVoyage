// Footer.jsx
'use client';
import React from 'react';
import styles from './Footer.module.scss'

const Footer = () => {
  return (
    <footer className={styles.container}>
        <p style={{ color: '#f1efee'}}>&copy; 2024 Bon Voyage. All rights reserved.</p>
    </footer>
  );
};

export default Footer;
