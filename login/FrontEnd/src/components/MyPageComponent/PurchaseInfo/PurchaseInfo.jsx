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

const PurchaseInfo = () => {

    const [purchaseData, setPurchaseData] = useState({
        date: '2024.01.20',
        productName: '우주먼지 키링',
        price: '20000'
    });

    const [orderStatus, setOrderStatus] = useState('결제 완료');
    const [isPurchaseDetailModalOpen, setIsPurchaseDetailModalOpen] = useState(false);
    const [isWriteReviewModalOpen, setIsWriteReviewModalOpen] = useState(false);

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
        onsubmit({ title, quillValue });
        setTitle('');
        setQuillValue('');
    };


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
            <h3 className={styles.title}>구매 내역</h3>
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
                  width: '650px',
                  height: '570px',
                  padding: '30px 30px 0px 30px',
                  borderRadius: '20px',
                }}>
                <div>
                  <h2 className={styles.title}>리뷰 작성하기</h2>
                </div>
                <div className={styles.formGroup}>
                  <input type="text" placeholder="제목을 입력하세요." />
                  <div>
                    <ReactQuill
                      theme="snow"
                      modules={modules}
                      formats={formats}
                      className={styles.editor}
                      value={quillValue || ""}
                    />
                  </div>
                  <div>
                    <input type="submit" />
                  </div>
                </div>
              </Rodal></div>
    </div>
    );
};

export default PurchaseInfo