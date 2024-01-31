'use client'
import React from 'react';
import Link from 'next/link';
import styles from './Navbar.module.scss';
import { ButtonContainer } from './ButtonContainer';


const Navbar = () => {
  return (
    <nav className={styles.navbar}>
      <Link href='/'>
        <div className={styles.logo}/>
      </Link>
      <ul>
        <ButtonContainer />
      </ul>
    </nav>
  );
};

export default Navbar;
