import React, { useState, useEffect, Component } from 'react';
import styles from './ArtistTalkDetail.module.scss'
import PropTypes from 'prop-types';
import axios from 'axios';
import Rodal from 'rodal';
import 'rodal/lib/rodal.css';

// eslint-disable-next-line react/prop-types
const ArtistTalkDetail = ({ postId, posts, onBackClick, isArtist, artistId }) => {
    const [newComment, setNewComment] = useState('');
    const [newTitle, setNewTitle] = useState('');
    const [newContent, setNewContent] = useState('');
    const [visible, setVisible] = useState(false);
    const [comments, setComments] = useState([]);
    // const [fieldValue, setFieldValue] = useState('');
    const [showWriteButton, setShowWriteButton] = useState(false);
    const [mentioncomment, setMentionComment] = useState([]);
    
    // eslint-disable-next-line react/prop-types
    const selectedPost = posts.find((post) => post.mentionseq === postId);

    const isArtistObject = isArtist;
    const UserId = isArtistObject.id.id
    const UserRole = isArtistObject.role

    useEffect(() => {
        const fetchArtistName = async () => {
          try {
            const backendUrl = 'https://i10a207.p.ssafy.io/api';
            const response = await axios.get(`${backendUrl}/artists/${artistId}`);
            const artistinfo = response.data;
    
            if (UserRole === 'artist' && artistinfo.id === UserId) {
              setShowWriteButton(true);
            }
            
          } catch (error) {
            console.error('Error fetching artist name:', error);
          }
        };
    
        fetchArtistName();
      }, [artistId]);

    const show = () => {
        setVisible(true);
      };
    
      const hide = () => {
        setVisible(false);
      };

    const handleBackClick = () => {
        onBackClick();
      };

    const handleCommentChange = (e) => {
        setNewComment(e.target.value);
    };

    const handleTitleChange = (e) => {
        const value = e.target.value;
        setNewTitle(value);
        console.log('New Title:', value);
        console.log(value.length)
    };

    const handleContentChange = (e) => {
        // const value = e.target.value;
        setNewContent(e.target.value);
    };

    // 작가의 말 삭제
    const handleDeletePost = () => {
        const backendUrl = 'https://i10a207.p.ssafy.io/api';
        axios.delete(`${backendUrl}/artistMentions/${postId}`)
          .then(response => {
            console.log('게시물 삭제 성공:', response.data);
            // const updatedPosts = posts.filter(post => post.id !== postId);
            // setPosts(updatedPosts);
            // 뒤로가기
            handleBackClick();
          })
          .catch(error => {
            console.error('게시물 삭제 실패:', error);
          });
      };

    // 작가의 말 수정
    const handleUpdatePost = async(event) => {
        event.preventDefault();

        console.log(newTitle)
        console.log(newContent)

        const updatedPost = {
            "artistId": UserId,
            "subject": newTitle,
            "content": newContent,
        }

        const backendUrl = 'https://i10a207.p.ssafy.io/api';

        const jsonString = JSON.stringify(updatedPost);
        axios.put(`${backendUrl}/artistMentions/${postId}`, jsonString, {
            headers: {
              'Content-Type': 'application/json;charset=UTF-8'
            }
          })
            .then(response => {
                console.log('게시물 수정 성공:', response.data);
                // handleBackClick();
                console.log(updatedPost)
            })
            .catch(error => {
                console.error('게시물 수정 실패:', error);
                console.log(updatedPost)
            });
    };

    // 작가의 말 댓글 목록
    // const fetchComments = async () => {
    //     try {
    //     const backendUrl = 'https://i10a207.p.ssafy.io/api';
    //     const response = await axios.get(`${backendUrl}/artistMentionComment/${postId}`);
    //     const HomepageComments = response.data;
    //     console.log(HomepageComments);
    //     const newMentionComment = HomepageComments.map(m_comment => ({
    //         content: m_comment.artistHompageCommentContent,
    //         // date: comment.artistHompageCommentContentCreatedDate,
    //         commentseq: m_comment.artistHomepageCommentSeq,
    //         member: m_comment.member,
    //         artist: m_comment.artist,
    //     }));
    //     // const NewContent = HomepageComments.artistHompageCommentContent
    //     // const NewSeq = HomepageComments.artistHomepageCommentSeq
    //     // const newComments = HomepageComments.artistHompageCommentContent
    //     setMentionComment(newMentionComment);
    //     // fetchComments();
    //     } catch (error) {
    //     console.error('데이터를 불러오는 중 에러 발생:', error);
    //     }
    // };

    // // 작가의 말 댓글 생성
    const handleCommentSubmit = () => {
        
        const backendUrl = 'https://i10a207.p.ssafy.io/api';
        const newCommentData = {
            'artistMentionSeq': postId,
            'memberId': UserId,
            'content': newComment,
        };

        const jsonString = JSON.stringify(newCommentData);

        axios.post(`${backendUrl}/artistMentionComment/new`, jsonString, {
            headers: {
              'Content-Type': 'application/json;charset=UTF-8'
            }
          })
            .then(response => {
                console.log('댓글 생성 성공:', response.data);
                fetchComments();
            })
            .catch(error => {
                console.error('댓글 생성 실패:', error);
            });

        setNewComment('');
    };

    // 작가의 말 댓글 수정


    // 작가의 말 댓글 삭제
    // const handleDeleteComment = () => {
    //     const backendUrl = 'https://i10a207.p.ssafy.io/api';
    //     axios.delete(`${backendUrl}/artistMentionComment/${postId}`)
    //       .then(response => {
    //         console.log('게시물 삭제 성공:', response.data);
    //         // const updatedPosts = posts.filter(post => post.id !== postId);
    //         // setPosts(updatedPosts);
    //         // 뒤로가기
    //         handleBackClick();
    //       })
    //       .catch(error => {
    //         console.error('게시물 삭제 실패:', error);
    //       });
    //   };
    
    return (
        <div className={styles.container}>
            <div className={styles.title_btn}>
                <div className={styles.title}>✏️ 작가의 말</div>
                <div className={styles.artist_btn}>
                    {showWriteButton && (
                        <>
                        <button className={styles.back_btn} onClick={show}>
                            수정하기
                        </button>
                        {/* <button className={styles.back_btn}>
                            삭제하기
                        </button> */}
                        <button className={styles.back_btn} onClick={handleDeletePost}>
                            삭제하기
                        </button>
                        </>
                    )}
                    <Rodal visible={visible} onClose={hide} showCloseButton={false}
                        customStyles={{
                        width: '65%',
                        height: '70%',
                        padding: '30px',
                        borderRadius: '20px',
                        overflow: 'hidden',
                        }}>
                        <div className={styles.modal_title}>
                        <div>글 수정하기</div>
                        <button className={styles.close_btn} onClick={hide}>X</button>
                        </div>
                        <form className={styles.modal_form} style={{ display: 'flex', flexDirection: 'column', height: '92%' }}>
                            <input type="text" className={styles.add_title}  value={newTitle || selectedPost.title} placeholder={"제목을 입력하세요"}
                            onChange={handleTitleChange}/>
                            <div className={styles.add_content2}>
                                <input type="text" value={newContent || selectedPost.content} onChange={handleContentChange}></input>
                            </div>
                            <div className={styles.submit} style={{ marginTop: '10px', textAlign: 'center' }}>
                            <button type="submit" className={styles.submit_btn} onClick={handleUpdatePost}>제출</button>
                            {/* <button type="submit" className={styles.submit_btn}>제출</button> */}
                            </div>
                        </form>
                    </Rodal>
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
                <div className={styles.title}>댓글({comments.length})</div>
                    <div className={styles.comment_input}>
                        <input type="text" placeholder='댓글을 입력하세요' value={newComment}
                        onChange={handleCommentChange}/>
                        <button onClick={handleCommentSubmit} className={styles.btn}>
                            작성
                        </button>
                        {/* <button>
                            작성
                        </button> */}
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
                        {/* {mentioncomment.map((comment, index) => (
                        <div className={styles.comment} key={index}>
                            <div className={styles.nickname}>{comment.id}</div>
                            <div>{comment.comment}</div>
                        </div>
                    ))} */}
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
    isArtist: PropTypes.object.isRequired,
  };

export default ArtistTalkDetail;