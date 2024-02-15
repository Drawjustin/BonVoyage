'use client'
import React, { useState, useEffect } from "react";
import styles from './AccountInfo.module.scss';
import { current } from "@reduxjs/toolkit";
import axios from 'axios';
import { useRouter } from 'next/navigation';

const AccountInfo = ({isArtist}) => {
    const navigate = useRouter();

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
    const [UserInfo, setUserInfo] = useState(null);

    const UserId = isArtist.id.id;
    const UserSeq = isArtist.id.seq;
    const UserRole = isArtist.role;

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

    // 계정 정보 불러오기
    useEffect(() => {
        const InfoRead = async () => {
          try {
            const backendUrl = 'https://i10a207.p.ssafy.io/api';
            let response;
            if (UserRole && UserRole === 'artist') {
                response = await axios.get(`${backendUrl}/artists/${UserId}`);
            } else if (UserRole && UserRole === 'member') {
                response = await axios.get(`${backendUrl}/members/${UserId}`);
            }
            const UserInfo = response.data;
            console.log(UserInfo);
            setUserInfo(UserInfo);

          } catch (error) {
            console.error('데이터를 불러오는 중 에러 발생:', error);
          }
        };
    
        InfoRead();
      }, [UserId, UserRole]);

    // 계정 삭제
    const UserDelete = () => {
        try {
        const backendUrl = 'https://i10a207.p.ssafy.io/api';
            if (UserRole && UserRole === 'artist') {
                axios.delete(`${backendUrl}/artists/${UserId}`);
            } else if (UserRole && UserRole === 'member') {
                axios.delete(`${backendUrl}/members/${UserId}`);
            } 

            console.log('탈퇴 완료')
            navigate.push('/');
        } catch (error) {
          console.error('데이터를 불러오는 중 에러 발생:', error);
        }
      };

    const handleSave = () => {
        console.log('저장된 데이터 : ', accountData);

        if (editableFields.password) {
            // 비밀번호 수정인 경우
            handleConfirmPasswordMatch(); // 비밀번호 일치 확인
            updatePassword(); // 비밀번호 수정 함수 호출
        }
    
        if (editableFields.nickname) {
            // 닉네임 수정인 경우
            updateNickname(); // 닉네임 수정 함수 호출
        }

        setEditableFields({
            password: false,
            nickname: false,
        });

        setCurrentPassword('');
        setNewPassword('');
        setConfirmNewPassword('');
        setPasswordMatchError('');
    };

    async function handlePasswordConfirm(){
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
        <div style={{alignItems: 'center'}}>
        <div style={{ width: '600px', alignItems: 'center', display:'flex', flexDirection:'column' }}>

            <div style={{display: 'flex', alignItems:'center', flexDirection:'row', width:'100%', height:'40%'}}>
                <h3 className={styles.title} style={{width: '500px'}}>계정 정보</h3>
                <button onClick={handleSave} style={{height:'30px', width: '100px'}} className={styles.btn}>전체 저장</button>
                <button onClick={UserDelete} style={{height:'30px', width: '100px'}} className={styles.btn}>회원 탈퇴</button>
            </div>
        <div>
        <div  className={styles.account_info}>
            <div>
            <div className={styles.info_item}>
                <p>이름</p>
                <span className={styles.container}>{UserInfo ? UserInfo.name : ''}</span>
            </div>


            <div className={styles.info_item}>
                <p>아이디</p>
                <span className={styles.container}>{UserId}</span>
            </div>


            <div className={styles.info_item}>
                <p>비밀번호</p>
                {editableFields.password ? (
                    <div>
                        <div style={{ marginLeft: '30%', width: '60%'}}>
                            <input
                            type="password"
                            placeholder="기존 비밀번호 입력"
                            value={currentPassword}
                            onChange={(e) => setCurrentPassword(e.target.value)}
                            />
                            <button onClick={handlePasswordConfirm}>확인</button>
                        </div>

                        <div style={{ marginLeft: '30%', width: '60%'}}>
                            {passwordMatchError && <span style={{ color: 'red', fontFamily: 'Gowun Dodum' }}>{passwordMatchError}</span>}
                            <input
                            type="password"
                            placeholder="새로운 비밀번호 입력"
                            value={newPassword}
                            onChange={handleNewPasswordChange}
                            />
                        </div>
                        <div style={{ marginLeft: '30%', width: '60%'}}>
                            <input
                            type="password"
                            placeholder="새로운 비밀번호 확인"
                            value={confirmNewPassword}
                            onChange={handleConfirmNewPasswordChange}
                            onBlur={handleConfirmPasswordMatch}
                            />
                        </div>
                  </div>
                ) : (
                    <span className={styles.container}></span>
                )}
                <button onClick={editableFields.password ? handleConfirmPasswordMatch : () => toggleEdit('password')}>
                    {editableFields.password ? '저장' : '수정'}
                </button>
            </div>

            <div className={styles.info_item}>
                <p>이메일  </p>
                <span className={styles.container}>{UserInfo ? UserInfo.email : ''}</span>
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
                    <span className={styles.container}>{UserInfo ? UserInfo.nickName : ''}</span>
                )}
                <button onClick={() => toggleEdit('nickname')} style={{ width: '45px' }}>
                    {editableFields.nickname ? '저장' : '수정'}
                </button>
            </div>
            </div>
        </div>
        </div>
        
    </div>
        </div>
        
    )
} 

export default AccountInfo