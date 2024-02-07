'use client'
import React, {useEffect, useState} from 'react';
import Link from 'next/link';
import styles from './Navbar.module.scss';
import {ButtonContainer} from './ButtonContainer';
import getCurrentUser from '@/app/actions/getCurrentUser';


const Navbar = () => {
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    const fetchCurrentUser = async () => {
      try {
        const user = await getCurrentUser();
        setCurrentUser(user);
      } catch (error) {
        console.error('Error fetching current user:', error);
      }
    };

    fetchCurrentUser();
  }, []);

  return (
    <nav className={styles.navbar}>
      <Link href='/'>
        <div className={styles.logo} />
      </Link>
      <ul>
        <ButtonContainer currentUser={currentUser} />
      </ul>
    </nav>
  );
};

export default Navbar;
