'use-client'
import React from 'react';
import loadingVideo from './loading.mp4';
import styles from './Loader.module.scss';

const Loader = () => {
  return (
    <div>
       <video autoPlay loop muted width="100%" height="auto">
         <source src={loadingVideo} type="video/mp4" />
         브라우저가 비디오 태그를 지원하지 않습니다.
      </video>
    </div>
  )
}

export default Loader

