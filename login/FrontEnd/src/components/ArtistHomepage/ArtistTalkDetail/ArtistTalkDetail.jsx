import React from 'react';
import styles from './ArtistTalkDetail.module.scss'
import PropTypes from 'prop-types';

// eslint-disable-next-line react/prop-types
const ArtistTalkDetail = ({ postId, posts, onBackClick }) => {
    // eslint-disable-next-line react/prop-types
    const selectedPost = posts.find((post) => post.id === postId);

    const handleBackClick = () => {
        // 뒤로가기 버튼 클릭 시, 부모 컴포넌트에서 전달한 콜백 함수 호출
        onBackClick();
      };
    
    return (
        <div className={styles.container}>
            <div className={styles.title_btn}>
                <div className={styles.title}>✏️ 작가의 말</div>
                <button className={styles.back_btn} onClick={handleBackClick}> 
                    뒤로가기
                </button>
            </div>
            <div className={styles.content_box}>
                <div className={styles.title}>
                    <div>{selectedPost.title}</div>
                    <div className={styles.date}>2024.01.25</div>
                </div>
                <div className={styles.content}>
                    {selectedPost.content}
                </div>
            </div>
            <div className={styles.comment_box}>
                <div className={styles.title}>댓글(2)</div>
                    <div className={styles.comment_input}>
                        <input type="text" placeholder='댓글을 입력하세요'/>
                        <button> 
                            작성
                        </button>
                    </div>
                    <div className={styles.comment_list}>
                        <div className={styles.comment}>
                            <div className={styles.nickname}>⛵ 고구마</div>
                            <div>안녕하세요 그림이 너무 제 취향이라 구독하고 갑니다~</div>
                        </div>
                        <div className={styles.comment}>
                            <div className={styles.nickname}>🚢 감자</div>
                            <div>작가 데뷔 축하드려요!</div>
                        </div>
                    </div>
                </div>
        </div>
    )
}

ArtistTalkDetail.propTypes = {
    postId: PropTypes.number.isRequired,
    posts: PropTypes.array.isRequired,
    onBackClick: PropTypes.func.isRequired,
  };

export default ArtistTalkDetail;