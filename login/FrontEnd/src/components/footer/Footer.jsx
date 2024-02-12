// Footer.jsx
'use client';
import React from 'react';
import styles from './Footer.module.scss'

const Footer = () => {
  return (
    <footer>
      <div className={styles.container}>
        <p style={{ color: '#f1efee'}}>&copy; 2024 Bon Voyage. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer;
