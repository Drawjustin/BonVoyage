'use client'
import React from 'react';
import Link from 'next/link';
import styles from './Navbar.module.scss';
import Image from 'next/image';
import { ButtonContainer } from './ButtonContainer';


const Navbar = () => {
  return (
    <nav className={styles.navbar}>
      <Link href='/'>
        <Image src='/assets/images/logo.png' className={styles.logo} width={0.3} height={0.3}/>
      </Link>
      <ul>
        <ButtonContainer />
      </ul>
    </nav>
  );
};

export default Navbar;
