import React from 'react';
import { Link } from 'react-router-dom';
import ArtistCard from '../../components/Signup/ArtistSignup/ArtistSignup'
import GuestCard from '../../components/Signup/GuestSignup/GuestSignup'
import styles from './SignupPage.module.scss';

// const ArtistForm = () => (
//   <div>
//     <Form1 />
//   </div>
// );

// const MemberForm = () => (
//   <div>
//     <Form2 />
//   </div>
// );

// const SignupPage = () => {
//   const [showArtistForm, setShowArtistForm] = useState(false);
//   const [showMemberForm, setShowMemberForm] = useState(false);

//   useEffect(() => {
//     // 페이지가 로드될 때 localStorage에서 상태를 읽어옴
//     const savedWriterForm = localStorage.getItem('showArtistForm') === 'true';
//     const savedMemberForm = localStorage.getItem('showMemberForm') === 'true';

//     if (savedWriterForm) {
//       setShowArtistForm(true);
//       setShowMemberForm(false);
//     } else if (savedMemberForm) {
//       setShowArtistForm(false);
//       setShowMemberForm(true);
//     }
//   }, []);

//   useEffect(() => {
//     // 새로 고침 이벤트를 감지하여 상태를 저장
//     const handleBeforeUnload = (event) => {
//       localStorage.setItem('showArtistForm', showArtistForm.toString());
//       localStorage.setItem('showMemberForm', showMemberForm.toString());
//       // 이벤트를 취소하여 페이지를 떠날 때 경고 메시지가 나타나지 않도록 함
//       event.returnValue = '';
//     };

//     window.addEventListener('beforeunload', handleBeforeUnload);

//     // 컴포넌트가 언마운트될 때 이벤트 리스너를 제거
//     return () => {
//       window.removeEventListener('beforeunload', handleBeforeUnload);
//     };
//   }, [showArtistForm, showMemberForm]);

//   useEffect(() => {
//     // 뒤로 가기 이벤트를 감지하여 상태를 복원
//     const handlePopState = () => {
//       const savedWriterForm = localStorage.getItem('showArtistForm') === 'true';
//       const savedMemberForm = localStorage.getItem('showMemberForm') === 'true';

//       if (savedWriterForm) {
//         setShowArtistForm(true);
//         setShowMemberForm(false);
//       } else if (savedMemberForm) {
//         setShowArtistForm(false);
//         setShowMemberForm(true);
//       } else {
//         setShowArtistForm(false);
//         setShowMemberForm(false);
//       }
//     };

//     window.addEventListener('popstate', handlePopState);

//     // 컴포넌트가 언마운트될 때 이벤트 리스너를 제거
//     return () => {
//       window.removeEventListener('popstate', handlePopState);
//     };
//   }, []);

//   const handleWriterCardClick = () => {
//     setShowArtistForm(true);
//     setShowMemberForm(false);
//     window.history.pushState({ showArtistForm: true }, '', window.location.href);
//   };

//   const handleMemberCardClick = () => {
//     setShowArtistForm(false);
//     setShowMemberForm(true);
//     window.history.pushState({ showMemberForm: true }, '', window.location.href);
//   };

//   const handleDeleteCard = () => {
//     setShowArtistForm(false);
//     setShowMemberForm(false);
//     window.history.replaceState(null, '', window.location.href);
//   };

//   return (
//     <div>
//       {showArtistForm || showMemberForm ? (
//         <div>
//           <button onClick={handleDeleteCard}>뒤로 가기</button>
//         </div>
//       ) : (
//         <div>
//             <div>회원가입</div>
//             <div className={styles.card}>
//             <div>
//                 <div onClick={handleWriterCardClick}>
//                 <Link to="/signup/artist"><ArtistCard /></Link>
//                 </div>
//             </div>

//             <div>
//                 <div onClick={handleMemberCardClick}>
//                 <GuestCard />
//                 </div>
//             </div>
//             </div>
//         </div>
//       )}

//       {showArtistForm && <ArtistForm />}
//       {showMemberForm && <MemberForm />}
//     </div>
//   );
// };

const SignupPage = () => {
    return (
        <div>
            <div className={styles.title}>회원가입</div>
            <div className={styles.cards}>
                <div className={styles.card}>
                    <Link to="/signup/artist"><ArtistCard /></Link>
                </div>
                <div className={styles.card}>
                    <Link to="/signup/member"><GuestCard /></Link>
                </div>
            </div>
        </div>
    )
}


export default SignupPage;