'use client'
import React, {useEffect, useState} from 'react';
import Link from 'next/link';
import styles from './Navbar.module.scss';
import {ButtonContainer} from './ButtonContainer';


const Navbar = () => {

  return (
    <nav className={styles.navbar} style={{ marginBottom: '10px' }}>
      <Link href='/'>
        <div className={`${styles.logo} ${styles.center}`} />
      </Link>
      <ul>
        <ButtonContainer />
      </ul>
    </nav>
  );
};

export default Navbar;
