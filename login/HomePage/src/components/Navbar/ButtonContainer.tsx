'use client'
import { styled } from '@mui/system';
import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
// import Link from 'next/link';

const Container = styled('nav')({
  width: '160px',
  display: 'flex',
  background: 'transparent',
  justifyContent: 'space-between',
});

const Button = styled('ul')({
  display: 'inline-block',
  position: 'relative',
  color: '#c4c4c4',
  fontSize: '15px',
  background: 'transparent',
  border: '0',
  marginRight: '25px',
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

interface buttonHandlerProps {}

export const ButtonContainer = ({}: buttonHandlerProps) => {
  const buttonList = [
    { text: '로그인', path: '/LoginPage' },
    { text: '회원가입', path: '/SignupPage' },
  ];
  const [selected, setSelected] = useState('');
  const navigate = useRouter();



  const buttonHandler = (text: string, path: string) => {
    setSelected(text);
    navigate.push(path);
  };

  return (
    <Container>
      {buttonList.map((item) => (
        <Button
          key={item.text}
          onClick={() => buttonHandler(item.text, item.path)}
        >
          {item.text === selected ? <Underline>{item.text}</Underline> : item.text}
        </Button>
      ))}
    </Container>
  );
};
