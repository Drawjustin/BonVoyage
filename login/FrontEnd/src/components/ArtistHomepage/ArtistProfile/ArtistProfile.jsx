'use client'
import React, { useRef, useState, useEffect } from 'react'
import styles from './ArtistProfile.module.scss'
import { Divider } from 'antd';
import axios from 'axios';
import img1 from './image.png'

const Profile = ({artistId}) => {
  const defaultImage = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
  const [Image, setImage] = useState(localStorage.getItem('profileImage') || defaultImage);
  const fileInput = useRef(null)
  const [artistName, setArtistName] = useState('');
  const [artistEmail, setArtistEmail] = useState('');

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

  // const artistName = `${artistId}`;

  useEffect(() => {
    const fetchArtistName = async () => {
      try {
        const backendUrl = 'https://i10a207.p.ssafy.io/api';
        const response = await axios.get(`${backendUrl}/artists/${artistId}`);
        const artistinfo = response.data;
        setArtistName(artistinfo.nickName);
        setArtistEmail(artistinfo.email);
      } catch (error) {
        console.error('Error fetching artist name:', error);
      }
    };

    fetchArtistName();
  }, [artistId]);
  
  return (
      <div className={styles.container}>
        {/* <input type='file' 
            style={{display:'none'}}
            accept='image/jpg,image/png,image/jpeg' 
            name='profile_img'
            onChange={onChange}
            ref={fileInput}/> */}
            <img src={img1} className={styles.profile_img}/>
        {/* <img src={Image}
          onClick={()=>{fileInput.current.click()}} 
          className={styles.profile_img} /> */}
        <div className={styles.profile_info}>
          <div className={styles.name}>
            <div style={{ paddingTop: '10px' }}>작가 {artistName}</div>
          </div>
          <Divider className={styles.devider}/>
          <div className={styles.info}>
            <button className={styles.port_btn} style={{ border: '#171de5 solid 1px', borderRadius: '8px', color: '#f1efee', fontFamily: 'Gowun Dodum' }}>📁 작가의 포트폴리오</button>
            <div>Email : {artistEmail} </div>
            </div>
          </div>
        </div>
  )
}

export default Profile;