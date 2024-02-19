'use client'
import React, { useEffect, useRef } from "react";
import html2canvas from 'html2canvas';

// 백엔드 컨트롤러 변수명 보고 맞춰야함
interface AuctionPaperProps {
    artist: string;
    buyer: string;
    artworkName: string;
    price: string;
    date: string;
    handleClose: () => void;
}

const AuctionPaper:any = ({ 
    artist, 
    buyer, 
    artworkName, 
    price, 
    date, 
    handleClose,
 }: AuctionPaperProps) => {
    const modalContentRef = useRef(null);

  useEffect(() => {

  
    const modalContent = modalContentRef.current;

    const captureAndDownloadImage = async () => {
        if (modalContent) {
            try {
          // Canvas 생성
          const canvas = await html2canvas(modalContent);

          const imageDataUrl = canvas.toDataURL('image/png');

          const downloadLink = document.createElement('a');
          downloadLink.href = imageDataUrl;
          downloadLink.download = `${artworkName}.png`;
    
          // 다운로드 링크 클릭 (이미지 다운로드)
          downloadLink.click();

        } catch (error) {
            console.error('Error capturing or downloading image:', error);
        }
    }
    };
    
    if (!handleClose) {
        captureAndDownloadImage();
    }
  }, [handleClose, artworkName]);

    
    
    return (
    <div style={{ marginLeft: '50vh' }}>
    <div style={{ textAlign: 'right', marginRight: '42vh' }}>
        <button onClick={() => window.location.reload()} style={{ borderRadius: '8px' , fontFamily: 'Gowun Dodum' }}>이미지 다운로드</button>
    </div>
    <div ref={modalContentRef} style={{ color: 'black', padding: '5vh', border: 'solid 2px lightgray', width: '40%', height: '80%', backgroundColor: 'lightgray', display: 'flex', flexDirection: 'column', alignItems:'start', margin: '10vh' }}>
        <div style={{  marginLeft: '1vh', width: '80%', height: '80%', border: '2px solid black', padding: '5vh' }}>
            <h2 style={{ textAlign: 'center', marginBottom: '4vh' }}>구매 계약서</h2>
            <p style={{ marginBottom: '2vh' }}>작가: {artist}</p>  
            <p>구매자: {buyer}</p>
            <p>작품명: {artworkName}</p>
            <p>낙찰가: {price}</p>
            <p>배송방법: 택배</p>
            <p>날짜 : {date}</p>
            <p style={{ marginBottom: '6vh', marginTop: '6vh' }}>계약 상대자는 위 건에 대한 계약을 체결하고, 신의에 따라 성실히 계약상의 의무를 이행할 것을 확약하며, 이 계약의 증거로써 계약서를 작성하여 당사자가 전자 서명 후 각각 1통씩 보관한다.</p>
            <p style={{ textAlign: 'right'}}>작가 서명 : _______________ (인)</p>
            <p style={{ textAlign: 'right'}}>구매자 서명 : ______________ (인)</p>
        </div>
    </div>

    </div>
)};

export default AuctionPaper