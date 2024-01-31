import React, { useRef, useState, useEffect } from 'react'
import styles from './ArtistProfile.module.scss'
import { Divider } from 'antd';

const Profile = () => {
  const defaultImage = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
  const [Image, setImage] = useState(localStorage.getItem('profileImage') || defaultImage);
  const fileInput = useRef(null)

  const onChange = (e) => {
    if(e.target.files[0]){
      const reader = new FileReader();
      reader.onload = () => {
        if (reader.readyState === 2) {
          const imageDataURL = reader.result;
          localStorage.setItem('profileImage', imageDataURL);
          setImage(imageDataURL);
        }
      };
      reader.readAsDataURL(e.target.files[0]);
        // setImage(e.target.files[0])
    } else {
        localStorage.removeItem('profileImage');
        setImage(defaultImage);
    }
  }

  useEffect(() => {
    const storedImage = localStorage.getItem('profileImage');
    if (storedImage) {
      setImage(storedImage);
    }
  }, []);
  
  return (
      <div className={styles.container}>
        <input type='file' 
            style={{display:'none'}}
            accept='image/jpg,image/png,image/jpeg' 
            name='profile_img'
            onChange={onChange}
            ref={fileInput}/>
        <img src={Image} 
          onClick={()=>{fileInput.current.click()}} 
          className={styles.profile_img} />
        <div className={styles.profile_info}>
          <div className={styles.name}>
            <div>작가 박슦껄</div>
          </div>
          <Divider className={styles.devider}/>
          <div className={styles.info}>
            <button className={styles.port_btn}>📁 작가의 포트폴리오</button>
            <div>Email : abcd123@naver.com </div>
            <button className = {styles.edit_btn}>프로필 수정</button>
          </div>
        </div>
      </div>
  )
}

export default Profile;