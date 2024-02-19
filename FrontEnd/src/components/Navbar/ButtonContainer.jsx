'use client'
import { styled } from '@mui/system';
import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import getCurrentUser from '@/app/actions/getCurrentUser';
// import Link from 'next/link';


const Container = styled('nav')({
  width: '18vw',
  display: 'flex',
  background: 'transparent',
  justifyContent: 'space-between',
  marginRight: '25px',
});

const Button = styled('ul')({
  display: 'inline-block',
  position: 'relative',
  color: '#c4c4c4',
  fontSize: '15px',
  background: 'transparent',
  border: '0',
  '&:hover': {
    fontWeight: 'bolder',
    cursor: 'pointer',
  },
  '&:after': {
    content: '""',
    position: 'absolute',
    width: '100%',
    transform: 'scaleX(0)',
    // 이게 밑줄 굵기임
    height: '2px',
    // 밑줄 위치
    top: '25px',
    left: 0,
    backgroundColor: '#0b679e',
    transformOrigin: 'center',
    transition: 'transform 0.25s ease-out',
  },
  '&:hover:after': {
    transform: 'scaleX(1)',
    transformOrigin: 'center',
  },
});

const Underline = styled('ul')({
  textDecoration: 'underline',
  textDecorationColor: '#0b679e',
  textDecorationThickness: '0.05em',
  fontWeight: 'bold',
});

export const ButtonContainer = () => {
  const navigate = useRouter();
  const session = getCurrentUser();
  const buttonList = [];

  if (!session) {
    buttonList.push(
      { text: '로그인', func: () => navigate.push('/LoginPage') },
      { text: '회원가입', func: () => navigate.push('/SignupPage') },
    );
  } else {
    buttonList.push(
      { text: '장바구니', func: () => navigate.push('/CartPage') },
      { text: '마이페이지', func: () => navigate.push('/MyPage') },
      { text: '로그아웃', func: () => {sessionStorage.clear(); alert('로그아웃 완료'); navigate.push('/')} },
    );
  }
  
  const [selected, setSelected] = useState('');

  const buttonHandler = (item) => {
    setSelected(item.text);
    item.func();
  };

  return (
    <Container>
      {buttonList.map((item) => (
        <Button
          key={item.text}
          onClick={() => buttonHandler(item)}
        >
          {item.text === selected ? <Underline>{item.text}</Underline> : item.text}
        </Button>
      ))}
    </Container>
  );
};
