'use client'
import React, { useState } from 'react';
import styles from './ResetPw.module.scss';
import axios from 'axios';
import Navbar from '@/components/Navbar/Navbar';
import getCurrentUser from '@/app/actions/getCurrentUser';

const ResetPw = () => {
    const currentUser = getCurrentUser();
    const [showResetForm, setShowResetForm] = useState(false);
    // axios 요청을 넣기 위해 하는 코드
    const [userId, setUserId] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [passwordsMatch, setPasswordsMatch] = useState(true);
 
    const handleResetClick = async() => {
        if (newPassword !== confirmPassword){
            // 비밀번호가 일치하지 않을 경우에는~
            setPasswordsMatch(false);
            return;
        }

        try {
            // 여기서 서버 api 엔드 포인트와 필요한 데이터에 따라 수정 필요함 ㄱ
            const response = await axios.post('/api/reset-password', {
                userId: userId,
                newPassword: newPassword,
            });

            // 서버로부터의 응답에 따른 처리
            if (response.status === 200) {
                // 비밀번호 재설정이 성공적으로 이루어진 경우
                console.log('비밀번호가 성공적으로 재설정되었습니다.');
                setPasswordsMatch(true); // 초기화 작업
                setShowResetForm(false); // 비밀번호가 잘 재설정되면 폼을 감춤
            } else {
                // 비밀전호 재설정이 실패한 경우
                console.error('비밀번호 갱신에 실패했습니다.');
            }
        } catch (error) {
            //비밀번호 재설정 요청 중 오류 발생한 경우
            console.error('비밀번호 갱신 요청 중 오류 발생:', error.message);
        }
    };

    return (
        <div className={styles.password_reset_container}>
            <h2>비밀번호 재설정</h2>
            {showResetForm ? (
            <>
                <p className={styles.infor_text}>비밀번호를 재설정하세요</p>
                {!passwordsMatch && (
                    <p className={styles.error_text}>비밀번호가 일치하지 않습니다.</p>
                )}
                {/* 비밃번호 재설정 폼 추가 */}
                <div className={styles.input_container}>
                    <label htmlFor="newPassword" className={styles.newPassword}>새 비밀번호</label>
                    <input 
                        type="password" 
                        id='newPassword' 
                        name='newPassword'
                        value={newPassword}
                        onChange={(e) => setNewPassword(e.target.value)} 
                    />
                </div>

                <div className={styles.input_container}>
                    <label htmlFor="confirmPassword">비밀번호 확인</label>
                    <input 
                        type="password" 
                        id='confirmPassword' 
                        name='confirmPassword'
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)} 
                    />
                </div>
                <button className={styles.reset_button} onClick={handleResetClick}>비밀번호 재설정</button>
            </>
        ) : (
            <>
                <p className={styles.infor_text}>아이디 확인 후 비밀번호를 재설정 할 수 있습니다.</p>
                {/* 아이디 입력 */}
                <div className={styles.input_container}>
                    <label htmlFor="userId">ID</label>
                    <input
                        type="text"
                        id='userId' 
                        name='userId' 
                        placeholder='아이디를 입력하세요.'
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)} 
                    />
                </div>
                <button className={styles.reset_button} onClick={() => setShowResetForm(true)}>확인</button>
            
            </>
            )}      
        </div>
    )
}

export default ResetPw