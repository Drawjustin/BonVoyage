'use client'
import React from 'react';
import Link from 'next/link';
import styles from './Navbar.module.scss';
import Image from 'next/image'

const Navbar = () => {
  return (
    <nav className={styles.navbar}>
      <Link href='/'>
        <Image src='/assets/images/logo.png' className={styles.logo} width={0.3} height={0.3}/>
      </Link>
      <ul>
        <Link href='/LoginPage'>로그인</Link>
        <Link href='/SignupPage'>회원가입</Link>
      </ul>
    </nav>
  );
};

export default Navbar;
