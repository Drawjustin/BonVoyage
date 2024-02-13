import React, { useState } from 'react';
import styles from './ArtistTalkDetail.module.scss'
import PropTypes from 'prop-types';

// eslint-disable-next-line react/prop-types
const ArtistTalkDetail = ({ postId, posts, onBackClick }) => {
    const [newComment, setNewComment] = useState('');
    // eslint-disable-next-line react/prop-types
    const selectedPost = posts.find((post) => post.id === postId);

    const handleBackClick = () => {
        onBackClick();
      };

    const handleCommentChange = (e) => {
        setNewComment(e.target.value);
    };
    
    // 댓글 생성
    const handleCommentSubmit = () => {
        console.log('New Comment:', newComment);
        setNewComment('');
    };
    
    return (
        <div className={styles.container}>
            <div className={styles.title_btn}>
                <div className={styles.title}>✏️ 작가의 말</div>
                <div>
                    <button className={styles.back_btn}> {/* 작가일때만 */}
                        수정하기
                    </button>
                    <button className={styles.back_btn}> {/* 작가일때만 */}
                        삭제하기
                    </button>
                    <button className={styles.back_btn} onClick={handleBackClick}> 
                        뒤로가기
                    </button>
                </div>
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
                        <input type="text" placeholder='댓글을 입력하세요' value={newComment}
                        onChange={handleCommentChange}/>
                        <button onClick={handleCommentSubmit}>
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
                    {/* <div className={styles.comment_list}>
                        {selectedPost.comments.map((comment, index) => (
                            <div className={styles.comment} key={index}>
                                <div className={styles.nickname}>{comment.nickname}</div>
                                <div>{comment.text}</div>
                            </div>
                        ))}
                    </div> */}
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