'use client'
import React, { useState, useMemo, useEffect } from 'react';
import styles from './ArtistTalk.module.scss';
import Rodal from 'rodal';
import 'rodal/lib/rodal.css';
import ArtistTalkDetail from '../ArtistTalkDetail/ArtistTalkDetail';
import PropTypes from 'prop-types';
import axios from 'axios';

const ArtistTalk = ({isArtist, artistId, artistSeq}) => {
  const [showMore, setShowMore] = useState(false);
  const [visible, setVisible] = useState(false);
  const [selectedDetailPost, setSelectedDetailPost] = useState(null);
  const [view, setView] = useState('list');
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [comment, setComment] = useState("");
  const [showWriteButton, setShowWriteButton] = useState(false);
  const [selectedPost, setSelectedPost] = useState(null);

  const [posts, setPosts] = useState([]);
  const [userTalks, setUserTalks] = useState([]);

  // 아티스트 / 멤버 판단
  const isArtistObject = isArtist;
  // console.log(isArtistObject.isArtist)
  const UserId = isArtistObject.id.id
  const UserRole = isArtistObject.role
  const UserSeq = isArtistObject.id.seq

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

  const [visibleItems, setVisibleItems] = useState(4); // 처음에는 4개만 보이도록
  const handleShowMoreClick = () => {
    setShowMore(!showMore);
    if (!showMore) {
      setVisibleItems(posts.length); // 모두 보이게
    } else {
      setVisibleItems(4); // 다시 처음 4개만 보이게
    }
  };
  // const handleGoBack = () => {
  //   setShowMore(false);
  //   setView('list');
  // };

  const show = () => {
    setVisible(true);
  };

  const hide = () => {
    setVisible(false);
  };

  const handleArtistTalkTitleClick = (mentionSeq) => {
    setSelectedDetailPost(mentionSeq);
    setView('detail');
  };

  // ArtistTalkDetail 컴포넌트에서 뒤로가기 버튼 눌렀을 때
  const handleBackFromDetail = () => {
    setView('list');
    setSelectedDetailPost(null); // 선택된 게시물 초기화
  };

  const handleTitleChange = (e) => {
    setTitle(e.target.value);
  };

  // const handleQuillChange = (content, delta, source, editor) => {
  //   setQuillValue(editor.getContents());
  // };

  const handleContentChange = (e) => {
    setContent(e.target.value);
  }

  const handleCommentChange = (e) => {
    setComment(e.target.value);
  }

  // 작가의 글 작성
  const mentionCreate = async(event) => {
    event.preventDefault();
    
    const backendUrl = 'https://i10a207.p.ssafy.io/api'
    
    if (title.trim() === "" || content === "") {
      console.error('제목 또는 내용이 비어 있습니다.');
      return;
    }

    // 현재 시간
    // const offset = new Date().getTimezoneOffset() * 60000;
    // const currentTime = new Date(Date.now() - offset);  

    const artistMentionData = {
      "subject" : title,
      "content" : content,
      "artistId": UserId,
    };
  
    const jsonString = JSON.stringify(artistMentionData);
  
    axios.post(`${backendUrl}/artistMentions/new`, jsonString, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      }
    })
      .then(response => {
        console.log('작가의 말 등록 성공 :', response.data);
        setTitle('');
        setContent('');
        hide();
        mentionRead();
      })
      .catch(error => {
        console.error('작가의 말 등록 실패(에러)', error);
      });
  }

  // 작가의 글 목록
  const mentionRead = async () => {
    try {
        const backendUrl = 'https://i10a207.p.ssafy.io/api';
        const response = await axios.get(`${backendUrl}/artistMentions/${artistSeq}`);
        const artistMentions = response.data;
        console.log(artistMentions)
        // artistMentions.subject
        const newPosts = artistMentions.map(mention => ({
          title: mention.subject,
          content: mention.content,
          id: mention.artistId,
          mentionseq: mention.artistMentionSeq,
          date: new Date(mention.createdDate).toLocaleDateString('ko-KR'),
          // date: mention.artistMentionCreatedDate,
        }));
        setPosts(newPosts);
    } catch (error) {
      console.error('데이터를 불러오는 중 에러 발생:', error);
    }
  };

  useEffect(() => {
    mentionRead();
  }, []);

  // 한 줄 응원 등록
  const CommentCreate = async(event) => {
    event.preventDefault();
    
    const backendUrl = 'https://i10a207.p.ssafy.io/api'
    
    if (comment === "") {
      console.error('내용이 비어 있습니다.');
      return;
    }

    const CommentData = {
      "artistHomepageCommentContent": comment,
      "member": UserSeq,
      "artist": artistSeq,
    };
  
    // const jsonString = JSON.stringify(CommentData);
  
    axios.post(`${backendUrl}/artistHomepageComment/new`, CommentData, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      }
    })
      .then(response => {
        console.log(CommentData)
        console.log('한 줄 응원 등록 성공 :', response.data);
        // setTitle('');
        // setContent('');
      })
      .catch(error => {
        console.error('한 줄 응원 등록 실패(에러)', error);
      });
  }
  
  // 한 줄 응원 목록
  useEffect(() => {
    const CommentRead = async () => {
      try {
        const backendUrl = 'https://i10a207.p.ssafy.io/api';
        const response = await axios.get(`${backendUrl}/artistHomepageComment/${artistSeq}`);
        const HomepageComments = response.data;
        console.log(HomepageComments);
        const newComments = HomepageComments.map(comment => ({
          content: comment.artistHomepageCommentContent,
          // date: comment.artistHompageCommentContentCreatedDate,
          commentseq: comment.artistHomepageCommentSeq,
        }));
        // const NewContent = HomepageComments.artistHompageCommentContent
        // const NewSeq = HomepageComments.artistHomepageCommentSeq
        // const newComments = HomepageComments.artistHompageCommentContent
        setUserTalks(newComments);
      } catch (error) {
        console.error('데이터를 불러오는 중 에러 발생:', error);
      }
    };

    CommentRead();
  }, []);

  return (
    <div className={styles.container}>
      {view === 'list' && (
        <div className={styles.content_box}>
          <div className={styles.artist_talk}>
            <div className={styles.title_btn}>
              <div className={styles.title}>✏️ 작가의 말</div>
              {showWriteButton && (
                <button className={styles.add_btn} onClick={show}>
                  작성하기
                </button>
              )}
              <Rodal visible={visible} onClose={hide} showCloseButton={false}
                customStyles={{
                  width: '700px',
                  height: '500px',
                  padding: '30px',
                  borderRadius: '20px',
                  overflow: 'hidden',
                }}>
                <div className={styles.modal_title}>
                  <div>글 작성하기</div>
                  <button className={styles.close_btn} onClick={hide}>X</button>
                </div>
                <form className={styles.modal_form} style={{ display: 'flex', flexDirection: 'column', height: '92%' }}>
                <input type="text" className={styles.add_title} value={title} placeholder="제목을 입력하세요." 
                onChange={handleTitleChange}/>
                <div className={styles.add_content2}>
                  <input type="text" value={content} onChange={handleContentChange}></input>
                </div>
                <div className={styles.submit} style={{ marginTop: '10px', textAlign: 'center' }}>
                <button type="submit" className={styles.submit_btn} onClick={mentionCreate}>제출</button>
                </div>
                </form>
              </Rodal>
            </div>
            <div className={styles.artist_talk_list}>
              {posts.length === 0 ? (
                  <div className={styles.no_post}>작성된 글이 없습니다.</div>
                ) : (
                  <>
                    {posts.slice(0, visibleItems).map((post) => (
                      <div key={post.mentionseq} onClick={() => handleArtistTalkTitleClick(post.mentionseq)} className={styles.artist_talk_title}>
                        <span className={styles.talk_title}>{post.title}</span>
                        <span className={styles.talk_date}>{post.date}</span>
                      </div>
                    ))}
                    <button className={styles.a_btn} onClick={handleShowMoreClick}>
                      {showMore ? '접기' : '더보기'}
                    </button>
                  </>
                )}
            </div>
          </div>
          <div className={styles.user_talk} style={{ display: showMore ? 'none' : 'block' }}>
            <span className={styles.title}>💗 한 줄 응원</span>
            {UserRole === 'member' && (
                <div className={styles.user_talk_input}> {/* 일반 회원일때만 */}
                <input type="text" value={comment} onChange={handleCommentChange}/>
                {/* <button>작성하기</button> */}
                <button onClick={CommentCreate}>작성하기</button>
              </div>
            )}
            <div className={styles.talk_list}>
              {userTalks.length === 0 ? (
                  <div className={styles.no_usertalk}>작성된 응원이 없습니다.</div>
                ) : (
                  <>
                    {userTalks &&
                      userTalks.map((comment) => (
                      <div key={comment.commentseq} className={styles.user_talk_list}>
                        <div className={styles.user_talk_title}>
                          <span className={styles.user_talk_1}>{comment.content}</span>
                        </div>
                      </div>
                    ))}
                    {/* <div key={comment.commentseq} className={styles.user_talk_list}>
                        <div className={styles.user_talk_title}>
                          <span className={styles.user_talk_1}>{comment.content}</span>
                        </div>
                      </div> */}
                  </>
                )}
            </div>
          </div>
        </div>
      )}
      {view === 'detail' && selectedDetailPost !== null && (
        <ArtistTalkDetail postId={selectedDetailPost} posts={posts} isArtist={isArtist} artistId={artistId}
        onBackClick={handleBackFromDetail}/>
      )}
    </div>
  );
};

ArtistTalk.propTypes = {
  posts: PropTypes.array,
};

export default ArtistTalk;