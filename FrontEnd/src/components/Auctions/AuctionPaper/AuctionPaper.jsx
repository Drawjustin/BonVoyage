'use client'
import React, { useEffect, useRef } from "react";
import html2canvas from 'html2canvas';

// 백엔드 컨트롤러 변수명 보고 맞춰야함

const AuctionPaper = ({ artist, buyer, artworkName, price, date, handleClose }) => {
    const modalContentRef = useRef(null);

  useEffect(() => {

  
    const modalContent = modalContentRef.current;

    if (modalContent) {
      // Canvas 생성
      const canvas = document.createElement('canvas');
      const context = canvas.getContext('2d');

      // Canvas 크기 설정 (모달 내용의 크기)
      canvas.width = modalContent.offsetWidth;
      canvas.height = modalContent.offsetHeight;

      // Canvas에 모달 내용 그리기
      html2canvas(document.body).then((canvas) => {
        document.body.appendChild(canvas);
      });
      // Canvas를 이미지로 변환
      const imageDataUrl = canvas.toDataURL('image/png');

      // 이미지 다운로드 링크 생성
      const downloadLink = document.createElement('a');
      downloadLink.href = imageDataUrl;
      downloadLink.download = `${artworkName}.png`;

      // 다운로드 링크 클릭 (이미지 다운로드)
      downloadLink.click();
    }
}, [handleClose]);

    
    
    return (
    <div className="modal-content" ref={modalContentRef}>
        <h2>구매 계약서</h2>
        <p>작가: {artist}</p>  
        <p>구매자: {buyer}</p>
        <p>작품명: {artworkName}</p>
        <p>낙찰가: {price}</p>
        <p>배송방법: 택배</p>
        <p>날짜 : {date}</p>
        <p>작가 서명 : ________________</p>
        <p>구매자 서명 : _______________</p>
        <button onClick={handleClose}>모달 닫기</button>
        <button onClick={() => window.location.reload()}>이미지 다운로드</button>
    </div>
)};

export default AuctionPaper