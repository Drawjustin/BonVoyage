import React, { useState } from "react";
import styles from './ChargePoint.module.scss';

import kakao from './kakaopay.png';
import naver from './/naverpay.png';
import toss from './tosspay.png';

const ChargePoint = () => {
    const [inputValue, setInputValue] = useState('');
    const [remainingPoints, setRemainingPoints] = useState(0);
    const [chargedPoints, setChargedPoints] = useState(0);

    const handleInputChange = (evnet) => {
        const value = event.target.value;
        setInputValue(value);
    };

    const updateChargedPoints = (value) => {
        const enteredPoints = parseInt(value, 10) || 0; // 입력값이 숫자가 아니면 0으로 처리
        const roundedPoints = Math.max(0, Math.floor(enteredPoints / 1000) * 1000); // 최소값을 0으로 설정
        setChargedPoints(roundedPoints);
    };

    const handleChargePoints = () => {
        const enteredPoints = parseInt(inputValue, 10) || 0; 
        const roundedPoints = Math.floor(enteredPoints / 1000) * 1000;
        setChargedPoints(chargedPoints + roundedPoints);
        setRemainingPoints(remainingPoints + roundedPoints);
        setInputValue('');

        updateRemainingPoints(remainingPoints + roundedPoints);
    };

    return (
        <div className={styles.payment_page}>
            {/* 포인트 충전 쪽 */}
            <div className={styles.left_section}>
                <div className={styles.blank}></div>
                <h2 className={styles.charge_point}>포인트 충전</h2>

                <br />

                <div>
                    <input
                        type="number"
                        id="pointInput"
                        className={styles.point_input}
                        placeholder="포인트를 입력하세요."
                        value={inputValue}
                        onChange={handleInputChange}
                        step="1000"
                        min="0"
                    />
                </div>

                
                <div className={styles.pointsContainer}><p className={styles.points}>남은 포인트</p><p className={styles.points}>{remainingPoints}</p></div>
                <div className={styles.pointsContainer}><p className={styles.points}>충전 후 포인트</p><p className={styles.points}>{chargedPoints}</p></div>    
                <br />
                <button className={styles.point_button} onClick={handleChargePoints}>충전</button>
            </div>

            

            {/* 결제 수단 쪽 */}
            <div className={styles.right_section}>
                <h3 className={styles.payment}>결제수단</h3>
                <div className={styles.payment_container}>
                    <div className={styles.payment_container2}>
                        <div className={styles.blank}></div>
                        <h4 className={styles.choice}>선택한 결제 방법</h4>
                        <div>  
                            <div className={styles.pay_align}>
                                <div className={styles.nav_button}>
                                    <img className={styles.pay} src={kakao} alt="카카오" />
                                </div>
                                <div className={styles.nav_button}>
                                    <img className={styles.pay} src={naver} alt="네이버" />
                                </div>
                                <div className={styles.nav_button}>
                                    <img className={styles.pay} src={toss} alt="토스" />
                                </div>
                            </div>
                        </div> 
                    </div>

                    <hr className={styles.line} />
                    <div className={styles.info}>결제 정보 안내</div>


                </div>
            </div>
        </div>
        
        
         
        
    );
};

export default ChargePoint