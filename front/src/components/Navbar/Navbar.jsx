import React from 'react';
import { Link } from 'react-router-dom';
import styles from './Navbar.module.scss';
import logo from './logo.png'

const Navbar = () => {
  return (
    <nav className={styles.navbar}>
      <Link to='/'>
        <img src={logo} className={styles.logo}></img>
      </Link>
      <ul>
        <Link to='/login'>로그인</Link>
        <Link to='/signup'>회원가입</Link>
      </ul>
    </nav>
  );
};

export default Navbar;
