import React, { useState } from 'react';
// import axios from 'axios';
import styles from './GuestInputSignup.module.scss';

const InputSignup = () => {
    const [username, setUsername] = useState('');
    const [userid, setUserid] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [nickname, setNickname] = useState('');
    const [email, setEmail] = useState('');
    const [phonenum, setPhonenum] = useState('');

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

    const SignUpSubmit = async (e) => {
      e.preventDefault();

      // 유효성 검사 실패한 경우
      if (!Object.values(errors).every((error) => error === null)) {
        console.log('모든 항목을 올바르게 입력하세요.');
        return;
      }

      // 방법 1
      // const data = {
      //   "id": userid,
      //   "name": username,
      //   "pw": password,
      //   "chekedpw": confirmPassword,
      //   "nickname": nickname,
      //   "email": email,
      //   "phonenumber": phonenum,
      // }

      // try {
      //   const response = await axios.post('url', data, {
      //     headers: {
      //       'Content-Type': 'application/json',
      //     },
      //   });
        
      //   console.log(response.data);
      // } catch (error) {
      //   console.error(error);
      // }

      // ----------------------------------------------------------

      // 방법 2
      // axios.post( 'url',
      //   {
      //     id: userid,
      //     name: username,
      //     pw: password,
      //     chekedpw: confirmPassword,
      //     nickname: nickname,
      //     email: email,
      //     phonenumber: phonenum,
      //   },
      //   {  // 
      //     headers: {
      //         'Content-type': 'application/json',
      //         // 'Accept': 'application/json'
      //     }
      //     }
      //   )
      //     .then((response) => {console.log(response.data); })
      //     .catch((error) => {console.error('Error!', error); });

      // ----------------------------------------------------------

      // 방법 3
      // var sendData = JSON.stringify({
      //   "id": userid,
      //   "name": username,
      //   "pw": password,
      //   "chekedpw": confirmPassword,
      //   "nickname": nickname,
      //   "email": email,
      //   "phonenumber": phonenum,
      // });
  
      // axios({
      //     method: "POST",
      //     url: 'url',
      //     data: sendData,
      //     headers: {'Content-type': 'application/json'}
      // }).then((res)=>{
      //     alert("성공");
      //     console.log(res.data);
      // }).catch(error=>{
      //     console.log("실패");
      //     console.log(error);
      // });

      // ----------------------------------------------------------

      // 방법 4
      // const dispatch = useDispatch();
      // const navigate = useNavigate();

      // const body = {
      //   email: email,
      //   name: name,
      //   password: password,
      // };
  
      // dispatch(registerUser(body)).then((res) => {
      //   if (res.payload.success) {
      //     navigate("/login");
      //   } else {
      //     alert("회원가입에 실패하셨습니다.");
      //   }
      // });

      // ----------------------------------------------------------
    
    };
      

    return (
        <form className={styles.form} onSubmit={SignUpSubmit}>
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
                    <input type="name" className={styles.form_input} placeholder='닉네임'
                    value={nickname} onChange={(e) => {
                      setNickname(e.target.value);
                  }}/>
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
                    <input type="text" className={styles.form_input_3} placeholder='연락처'
                    value={phonenum} onChange={(e) => {
                        setPhonenum(e.target.value);
                    }}/>
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