'use client'
import React, { useState, useMemo, useEffect } from 'react';
import styles from './ArtistTalk.module.scss';
import Rodal from 'rodal';
import 'rodal/lib/rodal.css';
import ReactQuill, { Quill } from "react-quill";
import 'react-quill/dist/quill.snow.css';
import { ImageActions } from '@xeger/quill-image-actions';
import { ImageFormats } from '@xeger/quill-image-formats';
import ArtistTalkDetail from '../ArtistTalkDetail/ArtistTalkDetail';
import PropTypes from 'prop-types';
import axios from 'axios';

Quill.register('modules/imageActions', ImageActions);
Quill.register('modules/imageFormats', ImageFormats);

const formats = [
  'header',
  'bold',
  'italic',
  'underline',
  'strike',
  'blockquote',
  'list',
  'bullet',
  'image',
  'align',
  'color',
  'background',
  'size',
  'h1',
  'float',
  'height',
  'width',
];

const ArtistTalk = () => {
  const [showMore, setShowMore] = useState(false);
  const [visible, setVisible] = useState(false);
  const [selectedDetailPost, setSelectedDetailPost] = useState(null);
  const [view, setView] = useState('list');
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  // const [posts, setPosts] = useState([]); // 포스트 데이터를 저장할 상태


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

  const modules = useMemo(() => ({
    imageActions: {},
    imageFormats: {},
    toolbar: {
      container: [
        [{ size: ['small', false, 'large', 'huge'] }],
        [{ align: [] }],
        ['bold', 'italic', 'underline', 'strike'],
        [{ list: 'ordered' }, { list: 'bullet' }],
        ["image"],
        [
          {
            color: [],
          },
          { background: [] },
        ],
      ],
    },
  }), []);


  // 작가의 말
  const posts = [
    { id: 1, title: '안녕하세요 여러분~ 오늘은 제가 드디어 작가로 데뷔를 하게 된 날입니다! 많은 관심 부탁', content: '여러분~ 오늘은 제가 드디어 작가로 데뷔를 하게 된 날입니다! 많은 관심 부탁드려요! 감사합니다' },
    { id: 2, title: '오늘은 팬 애칭을 정해보려고해요!', content: '내용' },
    { id: 3, title: '고구마들! 벌써 2024년 새해가 밝았어요~', content: '내용' },
    { id: 4, title: '고구마들의 하루는 어땠나요?', content: '내용' },
    { id: 5, title: '테스트글 1', content: '내용' },
    { id: 6, title: '테스트글 2', content: '내용' },
    { id: 7, title: '테스트글 3', content: '내용' },
    { id: 8, title: '테스트글 4', content: '내용' },
    { id: 9, title: '테스트글 4', content: '내용' },
    { id: 10, title: '테스트글 4', content: '내용' },
    { id: 11, title: '테스트글 4', content: '내용' },
  ];

  // 한 줄 응원
  const userTalks = [
    '안녕하세요 작가님 저는 고구마 2호가 된 사람입니다.',
    '고구마 1호입니다. 작가님 화이팅!',
    '응원합니다~~',
    '한 줄 응원 2',
    '한 줄 응원 3',
  ]

  const handleArtistTalkTitleClick = (postId) => {
    setSelectedDetailPost(postId);
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

  // 작가의 글 작성
  const mentionCreate = async(event) => {
    event.preventDefault();
    
    const backendUrl = 'https://i10a207.p.ssafy.io/api'
    
    if (title.trim() === "" || content === "") {
      console.error('제목 또는 내용이 비어 있습니다.');
      return;
    }

    // 현재 시간
    const offset = new Date().getTimezoneOffset() * 60000;
    const currentTime = new Date(Date.now() - offset);  

    const artistMentionData = {
        "artistMentionContent": content,
        "artistMentionSubject": title,
        // "artistMentionCreatedDate": "2024-02-07T10:00:00",
        "artistMentionCreatedDate": currentTime,
        "artistMentionIsdeleted": false,
        "artist": {
            "artist_seq": 1
        },
    };

    // const artistMentionData = {
    //   "artist_mention_content": quillValue.ops[0].insert,
    //   "artist_mention_subject": title,
    //   // "artistMentionCreatedDate": currentTime,
    //   // "artistMentionIsdeleted": false,
    //   // "artist": {
    //   //     "artist_seq": 1
    //   // },
    //   "artist_seq": 1,
    // };
  
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
      })
      .catch(error => {
        console.error('작가의 말 등록 실패(에러)', error);
      });
  }

  // 작가의 글 목록
  // useEffect(() => {
  //   const mentionRead = async () => {
  //     try {
  //       const backendUrl = 'https://i10a207.p.ssafy.io/api';
  //       const response = await axios.get(`${backendUrl}/artistMentions`);
  //       const artistMentions = response.data;
  //       console.log(artistMentions);
  //       const newPosts = artistMentions.map(mention => ({
  //         title: mention.artistMentionSubject,
  //         content: mention.artistMentionContent,
  //         date: mention.artistMentionCreatedDate,
  //       }));
  //       setPosts(newPosts);
  //     } catch (error) {
  //       console.error('데이터를 불러오는 중 에러 발생:', error);
  //     }
  //   };

  //   mentionRead();
  // }, []);

  return (
    <div className={styles.container}>
      {view === 'list' && (
        <div className={styles.content_box}>
          <div className={styles.artist_talk}>
            <div className={styles.title_btn}>
              <div className={styles.title}>✏️ 작가의 말</div>
              <button className={styles.add_btn} onClick={show}> {/* 작가일때만 */}
                작성하기
              </button>
              <Rodal visible={visible} onClose={hide} showCloseButton={false}
                customStyles={{
                  width: '65%',
                  height: '70%',
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
                {/* <div className={`${styles.add_content2} ql-container ql-snow`} style={{ flex: 1, overflowY: 'auto' }}>
                  <ReactQuill
                    theme="snow"
                    modules={modules}
                    formats={formats}
                    className={styles.quill_style}
                    value={quillValue || ""}
                    onChange={handleQuillChange}
                  />
                </div> */}
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
                      <div key={post.id} onClick={() => handleArtistTalkTitleClick(post.id)} className={styles.artist_talk_title}>
                        <span className={styles.talk_title}>{post.title}</span>
                        <span className={styles.talk_date}>2024.01.25</span>
                      </div>
                    ))}
                    {/* {posts.slice(0, visibleItems).map((post) => (
                      <div key={post.id} onClick={() => handleArtistTalkTitleClick(post.id)} className={styles.artist_talk_title}>
                        <span className={styles.talk_title}>{post.title}</span>
                        <span className={styles.talk_date}>{post.date}</span>
                      </div>
                    ))} */}
                    <button className={styles.a_btn} onClick={handleShowMoreClick}>
                      {showMore ? '접기' : '더보기'}
                    </button>
                  </>
                )}
            </div>
          </div>
          <div className={styles.user_talk} style={{ display: showMore ? 'none' : 'block' }}>
            <span className={styles.title}>💗 한 줄 응원</span>
            <div className={styles.user_talk_input}> {/* 일반 회원일때만 */}
              <input type="text" />
              <button>작성하기</button>
            </div>
            <div className={styles.talk_list}>
              {userTalks.length === 0 ? (
                  <div className={styles.no_usertalk}>작성된 응원이 없습니다.</div>
                ) : (
                  <>
                    {userTalks.map((talk, index) => (
                      <div key={index} className={styles.user_talk_list}>
                        <div className={styles.user_talk_title}>
                          <span className={styles.user_talk_1}>{talk}</span>
                        </div>
                      </div>
                    ))}
                  </>
                )}
            </div>
          </div>
        </div>
      )}
      {view === 'detail' && selectedDetailPost !== null && (
        <ArtistTalkDetail postId={selectedDetailPost} posts={posts} 
        onBackClick={handleBackFromDetail}/>
      )}
    </div>
  );
};

ArtistTalk.propTypes = {
  posts: PropTypes.array,
};

export default ArtistTalk;