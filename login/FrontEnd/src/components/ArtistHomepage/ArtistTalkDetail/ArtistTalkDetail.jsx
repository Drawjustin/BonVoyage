import React, { useState, useEffect, Component } from 'react';
import styles from './ArtistTalkDetail.module.scss'
import PropTypes from 'prop-types';
import axios from 'axios';
import Rodal from 'rodal';
import 'rodal/lib/rodal.css';

// eslint-disable-next-line react/prop-types
const ArtistTalkDetail = ({ postId, posts, onBackClick, isArtist }) => {
    const [newComment, setNewComment] = useState('');
    const [newTitle, setNewTitle] = useState('');
    const [newContent, setNewContent] = useState('');
    const [visible, setVisible] = useState(false);
    const [comments, setComments] = useState([]);
    // const [fieldValue, setFieldValue] = useState('');
    
    // eslint-disable-next-line react/prop-types
    const selectedPost = posts.find((post) => post.mentionseq === postId);

    const isArtistObject = JSON.parse(isArtist.isArtist);
    const UserId = isArtistObject.id
    const UserRole = isArtistObject.role

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
            "subject": newTitle,
            "content": newContent,
            "artistId": UserId,
        };

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
            })
            .catch(error => {
                console.error('게시물 수정 실패:', error);
            });
    };

    // 작가의 말 댓글 목록
    // useEffect(() => {
    //     fetchComments();
    // }, []);

    // const fetchComments = () => {
    //     const backendUrl = 'https://i10a207.p.ssafy.io/api';
    //     axios.get(`${backendUrl}/artistMentionComment/${postId}`)
    //         .then(response => {
    //             console.log('댓글 목록 가져오기 성공:', response.data);
    //             setComments(response.data);
    //         })
    //         .catch(error => {
    //             console.error('댓글 목록 가져오기 실패:', error);
    //         });
    // };

    // // 작가의 말 댓글 생성
    // const handleCommentSubmit = () => {
    //     const newCommentData = {
    //         postId: UserId,
    //         comment: newComment,
    //     };
    
    //     const backendUrl = 'https://i10a207.p.ssafy.io/api';
    //     axios.post(`${backendUrl}/artistMentionComment/new`, newCommentData)
    //         .then(response => {
    //             console.log('댓글 생성 성공:', response.data);
    //             fetchComments();
    //         })
    //         .catch(error => {
    //             console.error('댓글 생성 실패:', error);
    //         });

    //     setNewComment('');
    // };

    // 작가의 말 댓글 수정

    // 작가의 말 댓글 삭제
    
    return (
        <div className={styles.container}>
            <div className={styles.title_btn}>
                <div className={styles.title}>✏️ 작가의 말</div>
                <div>
                    {UserRole === 'artist' && (
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
                            <input type="text" className={styles.add_title}  value={newTitle || selectedPost.title} placeholder="제목을 입력하세요." 
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
                        {/* <button onClick={handleCommentSubmit}>
                            작성
                        </button> */}
                        <button>
                            작성
                        </button>
                    </div>
                    <div className={styles.comment_list}>
                        {/* <div className={styles.comment}>
                            <div className={styles.nickname}>⛵ 고구마</div>
                            <div>안녕하세요 그림이 너무 제 취향이라 구독하고 갑니다~</div>
                        </div>
                        <div className={styles.comment}>
                            <div className={styles.nickname}>🚢 감자</div>
                            <div>작가 데뷔 축하드려요!</div>
                        </div> */}
                        {comments.map((comment, index) => (
                        <div className={styles.comment} key={index}>
                            <div className={styles.nickname}>{comment.id}</div>
                            <div>{comment.comment}</div>
                        </div>
                    ))}
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


// class ArtistTalkDetail extends Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             newComment: '',
//             newTitle: '',
//             newContent: '',
//             visible: false,
//             comments: [],
//         };
//     }

//     show = () => {
//         this.setState({ visible: true });
//     };

//     hide = () => {
//         this.setState({ visible: false });
//     };

//     handleBackClick = () => {
//         this.props.onBackClick();
//     };

//     handleCommentChange = (e) => {
//         this.setState({ newComment: e.target.value });
//     };

//     handleTitleChange = (e) => {
//         this.setState({ newTitle: e.target.value });
//     };

//     handleContentChange = (e) => {
//         this.setState({ newContent: e.target.value });
//     };

//     handleUpdatePost = () => {
//         const { newTitle, newContent } = this.state;
//         const { postId } = this.props;
//         const { id: UserId } = JSON.parse(this.props.isArtist.isArtist);
//         const selectedPost = this.props.posts.find((post) => post.mentionseq === postId);
//         const updatedPost = {
//             "artistId": UserId,
//             "subject": newTitle || selectedPost.title,
//             "content": newContent || selectedPost.content,
//         };

//         const backendUrl = 'https://i10a207.p.ssafy.io/api';

//         const jsonString = JSON.stringify(updatedPost);
//         axios.put(`${backendUrl}/artistMentions/${postId}`, jsonString)
//             .then(response => {
//                 console.log('게시물 수정 성공:', response.data);
//                 this.handleBackClick();
//             })
//             .catch(error => {
//                 console.error('게시물 수정 실패:', error);
//             });
//     };
    
//     render() {
//         const { posts, postId, isArtist } = this.props;
//         const { newTitle, newContent, visible } = this.state;
//         const selectedPost = posts.find((post) => post.mentionseq === postId);
//         const UserRole = JSON.parse(isArtist.isArtist).role;

//         return (
//             <div className={styles.container}>
//                 <div className={styles.title_btn}>
//                     <div className={styles.title}>✏️ 작가의 말</div>
//                     <div>
//                         {UserRole === 'artist' && (
//                             <>
//                             <button className={styles.back_btn} onClick={this.show}>
//                                 수정하기
//                             </button>
//                             <button className={styles.back_btn}>
//                                 삭제하기
//                             </button>
//                             </>
//                         )}
//                         <Rodal visible={visible} onClose={this.hide} showCloseButton={false}
//                             customStyles={{
//                                 width: '65%',
//                                 height: '70%',
//                                 padding: '30px',
//                                 borderRadius: '20px',
//                                 overflow: 'hidden',
//                             }}>
//                             <div className={styles.modal_title}>
//                                 <div>글 수정하기</div>
//                                 <button className={styles.close_btn} onClick={this.hide}>X</button>
//                             </div>
//                             <form className={styles.modal_form} style={{ display: 'flex', flexDirection: 'column', height: '92%' }}>
//                                 <input type="text" className={styles.add_title} value={newTitle !== '' ? newTitle : selectedPost.title} placeholder="제목을 입력하세요." 
//                                 onChange={this.handleTitleChange}/>
//                                 <div className={styles.add_content2}>
//                                     <input type="text" value={newContent || selectedPost.content} onChange={this.handleContentChange}></input>
//                                 </div>
//                                 <div className={styles.submit} style={{ marginTop: '10px', textAlign: 'center' }}>
//                                     <button type="submit" className={styles.submit_btn} onClick={this.handleUpdatePost}>제출</button>
//                                 </div>
//                             </form>
//                         </Rodal>
//                         <button className={styles.back_btn} onClick={this.handleBackClick}> 
//                             뒤로가기
//                         </button>
//                     </div>
//                 </div>
//                 <div className={styles.content_box}>
//                     <div className={styles.title}>
//                         <div>{selectedPost.title}</div>
//                         <div className={styles.date}>2024.01.25</div>
//                     </div>
//                     <div className={styles.content}>
//                         {selectedPost.content}
//                     </div>
//                 </div>
//                 <div className={styles.comment_box}>
//                     <div className={styles.title}>댓글({this.state.comments.length})</div>
//                         <div className={styles.comment_input}>
//                             <input type="text" placeholder='댓글을 입력하세요' value={this.state.newComment}
//                             onChange={this.handleCommentChange}/>
//                             <button>
//                                 작성
//                             </button>
//                         </div>
//                         <div className={styles.comment_list}>
//                             {/* Comments */}
//                         </div>
//                 </div>
//             </div>
//         );
//     }
// }

// ArtistTalkDetail.propTypes = {
//     postId: PropTypes.number.isRequired,
//     posts: PropTypes.array.isRequired,
//     onBackClick: PropTypes.func.isRequired,
//     isArtist: PropTypes.object.isRequired,
// };

// export default ArtistTalkDetail
