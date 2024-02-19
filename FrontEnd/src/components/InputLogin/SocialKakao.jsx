'use client'
import React from 'react';
import KakaoLogin from 'react-kakao-login';

const SocialKakao = () => {

    const kakaoClientId = '3b5b638c90307bb8253e7f6db8706b63'
    const kakaoOnSuccess = async (data)=>{
      	console.log(data)
        const idToken = data.response.id_token  // 인가코드 백엔드로 전달
    }
    const kakaoOnFailure = (error) => {
        console.log(error);
    };
    return(
        <>
          <KakaoLogin
              token={kakaoClientId}
              onSuccess={kakaoOnSuccess}
              onFail={kakaoOnFailure}
          />
        </>
    )
}

export default SocialKakao