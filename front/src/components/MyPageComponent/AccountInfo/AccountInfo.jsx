import React, { useState } from "react";
import styles from './AccountInfo.module.scss';
import { current } from "@reduxjs/toolkit";


const AccountInfo = () => {
    // 가상의 정보 데이터
    const [accountData, setAccountData] = useState({
        name: '박수민',
        username: 'breathin_suemin',
        password: 'ssafy1234',
        email: 'breathin_suemin@naver.com',
        nickname: '고구마',

    });

    // 수정 가능한 항목들
    const [editableFields, setEditableFields] = useState({
        password: false,
        nickname: false,
    });

    const [currentPassword, setCurrentPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [passwordMatchError, setPasswordMatchError] = useState('');


    // 저장 버튼 누르면 호출되는 함수
    const toggleEdit = (field) => {
        setEditableFields((prevEditableFields) => ({
            ...prevEditableFields,
            [field]: !prevEditableFields[field],
        }));

        setCurrentPassword('');
        setNewPassword('');
        setConfirmNewPassword('');
        setPasswordMatchError('');
    };

    // 수정 가능한 항목 다시 초기화
    const handleChange = (field, value) => {
        setAccountData((prevAccountData) => ({
            ...prevAccountData,
            [field]: value,
        }));
    };

    const handleSave = () => {
        console.log('저장된 데이터 : ', accountData);

        setEditableFields({
            password: false,
            nickname: false,
        });

        setCurrentPassword('');
        setNewPassword('');
        setConfirmNewPassword('');
        setPasswordMatchError('');
    };

    const handlePasswordConfirm = () => {
        if (accountData.password === currentPassword) {
            setEditableFields({
                password: true,
                nickname: false,
            });
        } else {
            setPasswordMatchError('기존 비밀 번호가 일치하지 않습니다.');
        }
    };

    const handleNewPasswordChange = (e) => {
        setConfirmNewPassword(e.target.value);
    };

    const handleConfirmNewPasswordChange = (e) => {
        setConfirmNewPassword(e.target.value);
    };

    const handleConfirmPasswordMatch = () => {
        if (newPassword !== confirmNewPassword) {
            setPasswordMatchError('새 비밀번호가 일치 하지 않습니다.');
        } else {
            setPasswordMatchError('');
        }
    };

    


    return (
        <div>
        <h3 className={styles.title}>계정 정보</h3>
        <div  className={styles.account_info}>
            <div>
            <div className={styles.info_item}>
                <p>이름</p>
                <span className={styles.container}>{accountData.name}</span>
            </div>


            <div className={styles.info_item}>
                <p>아이디</p>
                <span className={styles.container}>{accountData.username}</span>
            </div>


            <div className={styles.info_item}>
                <p>비밀번호</p>
                {editableFields.password ? (
                    <>
                    <input
                      type="password"
                      placeholder="기존 비밀번호 입력"
                      value={currentPassword}
                      onChange={(e) => setCurrentPassword(e.target.value)}
                    />
                    <button onClick={handlePasswordConfirm}>확인</button>
                    {passwordMatchError && <span style={{ color: 'red' }}>{passwordMatchError}</span>}
                    <input
                      type="password"
                      placeholder="새로운 비밀번호 입력"
                      value={newPassword}
                      onChange={handleNewPasswordChange}
                    />
                    <input
                      type="password"
                      placeholder="새로운 비밀번호 확인"
                      value={confirmNewPassword}
                      onChange={handleConfirmNewPasswordChange}
                      onBlur={handleConfirmPasswordMatch}
                    />
                  </>
                ) : (
                    <span className={styles.container}></span>
                )}
                <button onClick={editableFields.password ? handleConfirmPasswordMatch : () => toggleEdit('password')}>
                    {editableFields.password ? '저장' : '수정'}
                </button>
            </div>


            <div className={styles.info_item}>
                <p>이메일  </p>
                <span className={styles.container}>{accountData.email}</span>
            </div>



            <div className={styles.info_item}>
                <p>닉네임  </p>
                {editableFields.nickname ? (
                    <input
                        type="text"
                        value={accountData.nickname}
                        onChange={(e) => handleChange('nickname', e.target.value)}
                    />
                ) : (
                    <span className={styles.container}>{accountData.nickname}</span>
                )}
                <button onClick={() => toggleEdit('nickname')}>
                    {editableFields.nickname ? '저장' : '수정'}
                </button>
            </div>
            <button onClick={handleSave}>전체 저장</button>
            </div>
        </div>
    </div>
    )
} 

export default AccountInfo