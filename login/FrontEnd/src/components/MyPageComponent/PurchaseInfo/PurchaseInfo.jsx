'use client'
import React, { useState } from "react";
import styles from './PurchaseInfo.module.scss';
import firstProduct from './first_product.jpg';
// import WriteReview from "./WriteReview";
import PurchaseDetail from './PurchaseDetail';
import Rodal from 'rodal';
import 'rodal/lib/rodal.css';
import "react-quill/dist/quill.snow.css";
import ReactQuill from "react-quill";


// 새로운 리뷰를 표시할 컴포넌트
const ReviewComponent = ({ review }) => {
    return (
      <div>
        <h3>{review.title}</h3>
        <p>{review.content}</p>
        {/* 여기에 리뷰 추가적인 정보 렌더링 */}
      </div>
    );
  };


const PurchaseInfo = () => {

    const [purchaseData, setPurchaseData] = useState({
        date: '2024.01.20',
        productName: '우주먼지 키링',
        price: '20000'
    });

    const [orderStatus, setOrderStatus] = useState('결제 완료');
    const [isPurchaseDetailModalOpen, setIsPurchaseDetailModalOpen] = useState(false);
    const [isWriteReviewModalOpen, setIsWriteReviewModalOpen] = useState(false);
    const [reviews, setReviews] = useState([]); // 리뷰를 저장할 상태 추가


    const handleReviewButtonClick = () => {
        setIsWriteReviewModalOpen(true);
    };

    const handleConfirmButtonClick = () => {
        if (orderStatus === '결제 완료') {
            setOrderStatus('배송 완료');
        } else {
            // 리뷰 작성 로직 (모달로 리뷰 작성 만들거임 ㄱㄷㄱㄷ)
            setIsWriteReviewModalOpen(true);
        }
    };
    
    const handleCloseReviewModal = () => {
        setIsWriteReviewModalOpen(false);
    };

    const handleReviewSubmit = (reviewText) => {
        console.log('리뷰 내용', reviewText);
        setIsWriteReviewModalOpen(false);
    }

    const [title, setTitle] = useState('');
    const [quillValue, setQuillValue] = useState("");

    const handleTitleChange = (e) => {
        setTitle(e.target.value);
    };
    
    const handleQuillChange = (content, delta, source, editor) => {
        setQuillValue(editor.getContents());
    };

    const handleSubmit = () => {
      console.log('제목', title);
      console.log('내용', quillValue);
      // onsubmit({ title, quillValue });
      setTitle('');
      setQuillValue('');
      setIsWriteReviewModalOpen(false); // 제출 후 모달 창 닫기
    };

    const renderReviews = () => {
        return reviews.map((review, index) => (
          <ReviewComponent key={index} review={review} />
        ));
      };

    // const handleSubmit = async () => {
    //     try {
    //       // 서버에 리뷰 전송
    //       const response = await fetch('https://your-api-endpoint.com/reviews', {
    //         method: 'POST',
    //         headers: {
    //           'Content-Type': 'application/json',
    //         },
    //         body: JSON.stringify({
    //           title,
    //           content: quillValue,
    //         }),
    //       });
      
    //       if (!response.ok) {
    //         throw new Error('리뷰를 저장하는 데 문제가 발생했습니다.');
    //       }
      
    //       console.log('리뷰가 성공적으로 저장되었습니다.');
      
    //       // 여기에서 게시판에 리뷰를 추가하는 로직을 추가할 수 있습니다.
      
    //       // 예시: 리뷰가 성공적으로 저장된 후 게시판 갱신
    //       // 이 부분은 실제로 서버에서 리뷰를 가져오는 로직으로 대체되어야 합니다.
    //       const updatedReviews = await fetch('https://your-api-endpoint.com/reviews');
    //       const reviewsData = await updatedReviews.json();
      
    //       // 게시판 갱신 로직
    //       // updateReviewsBoard(reviewsData);
      
    //       // 나머지 작업 수행
    //       setTitle('');
    //       setQuillValue('');
    //       handleCloseReviewModal();
    //     } catch (error) {
    //       console.error('리뷰를 저장하는 동안 오류가 발생했습니다.', error);
    //     }
    //   };

    const handlePurchaseDetailButtonClick = () => {
        setIsPurchaseDetailModalOpen(true);
    };

    const handleClosePurchaseDetailModal = () => {
        setIsPurchaseDetailModalOpen(false);
    };
    
    const modules = {
        toolbar: [
            [{ header: [1, 2, false] }],
            ["bold", "italic", "underline", "strike", "blockquote"],
            [
                { list: "ordered" },
                { list: "bullet" },
                { indent: "-1" },
                { indent: "+1" },
            ],
            ["link", "image"],
            [{ align: [] }, { color: [] }, { background: [] }], // dropdown with defaults from theme
            ["clean"],
        ],
    };
    
    const formats = [
        "header",
        "bold",
        "italic",
        "underline",
        "strike",
        "blockquote",
        "list",
        "bullet",
        "indent",
        "link",
        "image",
        "align",
        "color",
        "background",
    ];
    



    return (
        <div className={styles.frame_parent}>
        <div className={styles.frame}>
            <h3 className={styles.title} style={{ color: '#f1efee', fontFamily: 'Gowun Dodum' }}>구매 내역</h3>
            <div className={styles.bigContainer}>
                <div className={styles.status}>{orderStatus}</div>
                <div className={`${styles.purchaseContainer} ${orderStatus === '결제 완료' ? styles.completed : styles.shipping}`}>

                <img src={firstProduct} alt="first" className={styles.productImage} />
                
                <div className={styles.productInfo}>
                    <div className={styles.additionalInfo}>
                        <div className={styles.date}><span className={styles.bold}>구매일자  </span>{purchaseData.date}</div>
                        <div className={styles.productName}><span>작품명 </span>  {purchaseData.productName}</div>
                        <div className={styles.price}><span className={styles.bold}>가격 </span> {purchaseData.price} (원)</div>
                    </div>
                </div>
                <div className={styles.buttons}>

                    <button className={styles.detailsButton} onClick={handlePurchaseDetailButtonClick}>결제 상세</button>
                        <Rodal
                            visible={isPurchaseDetailModalOpen}
                            onClose={handleClosePurchaseDetailModal}
                            animation="zoom"
                            customStyles={{
                                width: '600px',
                                height: '530px',
                                padding: '20px',
                                borderRadius: '10px',

                            }}
                        >
                            <PurchaseDetail />
                        </Rodal>
                    <button className={styles.confirmButton} onClick={handleConfirmButtonClick}>
                        {orderStatus === '결제 완료' ? '구매 확정' : '리뷰 작성'}
                    </button>
                </div>
                </div>
            </div>
        </div>


        <div className={styles.writeReviewContainer}>
        <Rodal
                visible={isWriteReviewModalOpen} 
                onClose={handleCloseReviewModal}
                animation="zoom"
                customStyles={{
                  width: '65%',
                  height: '80%',
                  padding: '30px',
                  borderRadius: '20px',
                  overflow: 'hidden',
                }}>
                <div>
                  <h2 className={styles.title}>리뷰 작성하기</h2>
                </div>
                <div className={styles.formGroup}>
                  <input 
                    type="text" 
                    placeholder="제목을 입력하세요."
                    value={title}
                    onChange={handleTitleChange} 
                  />
                  <div className={styles.editorWrapper}>
                    <ReactQuill
                      theme="snow"
                      modules={modules}
                      formats={formats}
                      className={styles.editor}
                      value={quillValue || ""}
                      onChange={handleQuillChange}
                    />
                  </div>
                  <div className={styles.submitButtonWrapper}>
                    <input type='submit' onClick={handleSubmit} style={{ color: '#f1efee', backgroundColor: '#171de5', borderRadius: '8px', border: '#171de5 solid 1px', fontSize: 'medium' }}></input>
                  </div>
                </div>
              </Rodal></div>
    </div>
    );
};

const ReviewBoard = ({ renderReviews }) => {
    return (
      <div>
        {renderReviews()} {/* 리뷰를 렌더링하는 함수 호출 */}
      </div>
    );
  };

export default PurchaseInfo