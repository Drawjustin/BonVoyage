'use client'
import React, { useState } from 'react'
import styles from './MemberInputSignup.module.scss'

const InputSignup = () => {
    const [username, setUsername] = useState('');
    const [userid, setUserid] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [email, setEmail] = useState('');

    const [errors, setErrors] = useState({});

    // 이름 유효성 검사
    const validateUsername = (value) => {
        if (value.length > 15) {
        // if (value.length < 3 || value.length > 15) {
          setErrors((prevErrors) => ({
            ...prevErrors,
            username: '* 사용자명은 15자 이하이어야 합니다.',
          }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, username: null }));
        }
    };

    // 아이디 유효성 검사
    const validateUserid = (value) => {
        if (value.length < 3 || value.length > 15) {
          setErrors((prevErrors) => ({
            ...prevErrors,
            userid: '* 아이디는 3자 이상 15자 이하이어야 합니다.',
          }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, userid: null }));
        }
    };

    // 비밀번호
    const validatePassword = (value) => {
        if (value.length < 6) {
          setErrors((prevErrors) => ({
            ...prevErrors,
            password: '* 비밀번호는 최소 6자 이상이어야 합니다.',
          }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, password: null }));
        }
    };

    // 비밀번호 확인
    const validateConfirmPassword = (value) => {
        if (value !== password || value.trim() === '') {
          setErrors((prevErrors) => ({
            ...prevErrors,
            confirmPassword: '* 비밀번호가 일치하지 않습니다.',
          }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, confirmPassword: null }));
        }
    };

    // 이메일 유효성 검사
    const validateEmail = (value) => {
        if (!/\S+@\S+\.\S+/.test(value) || value.trim() === '') {
          setErrors((prevErrors) => ({ ...prevErrors, email: '* 올바른 형식의 이메일을 입력하세요.' }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, email: null }));
        }
      };
    return (
        <form className={styles.form}>
            <div className={styles.form_title}>개인 회원가입</div>
            <div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>이름</div>
                    <input type="name" className={styles.form_input} placeholder='이름' 
                    value={username} onChange={(e) => {
                        setUsername(e.target.value);
                        validateUsername(e.target.value);
                    }}/>
                    {errors.username && <span className={styles.error}>{errors.username}</span>}
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>아이디</div>
                    <div className={styles.form_inputbtn}>
                        <input type="text" className={styles.form_input_3} placeholder='아이디'
                        value={userid} onChange={(e) => {
                            setUserid(e.target.value);
                            validateUserid(e.target.value);
                        }}/>
                        <button className={styles.input_btn}>중복 확인</button>
                    </div>
                    {errors.userid && <span className={styles.error}>{errors.userid}</span>}
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>비밀번호</div>
                    <input type="password" className={styles.form_input} placeholder='비밀번호'
                    value={password} onChange={(e) => {
                        setPassword(e.target.value);
                        validatePassword(e.target.value);
                    }}/>
                    {errors.password && <span className={styles.error}>{errors.password}</span>}
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>비밀번호 확인</div>
                    <input type="password" className={styles.form_input} placeholder='비밀번호 확인'
                    value={confirmPassword} onChange={(e) => {
                        setConfirmPassword(e.target.value);
                        validateConfirmPassword(e.target.value);
                    }}/>
                    {errors.confirmPassword && <span className={styles.error}>{errors.confirmPassword}</span>}
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>닉네임</div>
                    <input type="name" className={styles.form_input} placeholder='닉네임'/>
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>이메일</div>
                    <input type="text" className={styles.form_input} placeholder='이메일'
                    value={email} onChange={(e) => {
                        setEmail(e.target.value);
                        validateEmail(e.target.value);
                    }}/>
                    {errors.email && <span className={styles.error}>{errors.email}</span>}
                </div>
                <div className={styles.form_element}>
                    <div className={styles.form_name}>연락처</div>
                    <div className={styles.form_inputbtn}>
                    <input type="text" className={styles.form_input_3} placeholder='연락처'/>
                        <button className={styles.input_btn}>인증하기</button>
                    </div>
                </div>
            </div>
            <div>
                <button type="submit" className={styles.submit_btn}>가입하기</button>
            </div>
        </form>
    )
}

export default InputSignup