import React, { useState } from 'react';
import styles from './InputLogin.module.scss';
import { Link } from 'react-router-dom';
import { FaUser, FaLock } from 'react-icons/fa';

const InputLogin = () => {
  const [isArtist, setIsArtist] = useState(false);

  const handleArtistToggle = () => {
    setIsArtist(true);
  };

  const handlePersonalToggle = () => {
    setIsArtist(false);
  };
    const handleLogin = () => {
    // 로그인 처리 로직 추가
    console.log(`Logging in as ${isArtist ? 'Artist' : 'User'}`);

    // 실제 프로젝트에서는 서버로 인증 요청을 보내야 합니다.
  };

  return (
    <div className={styles.login_container}>
      <h2>Login</h2>
      <div className={styles.toggle_container}>
        <label>
          <button 
            onClick={handleArtistToggle}
            style={{ backgroundColor: isArtist ? '#7377b6' : 'white',
            color: isArtist ? 'white' : 'black' }}
            >
            작가</button>
        </label>
        <br />

        <label>
          <button
            onClick={handlePersonalToggle}
            style={{ backgroundColor: !isArtist ? '#7377b6' : 'white',
            color: !isArtist ? 'white' : 'black' }}
          >
          개인
          </button>
        </label>

        <br />

      </div>

      <div className={styles.container}>
      <div className={styles.input_container}>
        <div className={styles.icon_input}>
          <FaUser className={styles.icon} />
          <input
            type="text"
            placeholder={isArtist ? '작가 ID' : '개인 ID'}
          />
        </div>

        <div className={styles.icon_input}>
          <FaLock className={styles.icon} />
          <input
            type="password"
            placeholder="Password"
            />
        </div>
      </div>
      <div className={styles.button_container}>
      <button className={styles.login_button} onClick={handleLogin}>
        로그인
      </button></div>
    </div>
      
      <div className={styles.search_user_info_div}>
        <Link to='/find_id'>아이디 찾기</Link>
        <span> | </span>
        <Link to='/reset_pw'>비밀번호 찾기</Link>
      </div>

    </div>
  );
};

export default InputLogin;