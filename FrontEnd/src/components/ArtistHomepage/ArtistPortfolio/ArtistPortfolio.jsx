'use client'
import React, { useState } from 'react'
import styles from './ArtistPortfolio.module.scss'

import { DndProvider, useDrag, useDrop } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import { Timeline } from 'antd';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider1 from '../Slider1/Slider1';

import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import 'swiper/css/scrollbar';

const DraggableTimelineItem = ({ item, index, moveItem }) => {
  const [, ref] = useDrag({
    type: 'TIMELINE_ITEM',
    item: { index },
  });

  const [, drop] = useDrop({
    accept: 'TIMELINE_ITEM',
    hover: (draggedItem) => {
      if (draggedItem.index !== index) {
        moveItem(draggedItem.index, index);
        draggedItem.index = index;
      }
    },
  });

  return (
    <div ref={(node) => ref(drop(node))} style={{ cursor: 'grab' }} className={styles.edit_item}>
      <div style={{marginRight: '10px'}}>=</div>
      <div>{item.children}</div>
    </div>
  );
};

const ArtistPortfolio = () => {
  const [isEditMode, setIsEditMode] = useState(false);
  const [newItemText, setNewItemText] = useState('');
  const [timelineItems, setTimelineItems] = useState([
    { color: '#171de5', children: '2018 “코리안 팝아트”, 하남문화예술회관, 경기' },
    { color: '#171de5', children: '2017 “Soul of City” 특별전, UIA 2017 Seoul World Architects Congress, COEX Hall D, 서울' },
    { color: '#171de5', children: '2017 “미디어아트 인 울산 -과학과 예술의 융합”, 울산문화예술회관, 울산' },
    { color: '#171de5', children: '2016 “내 안의 또 다른 나”, 가일미술관, 경기' },
    { color: '#171de5', children: '2015 “사이공간”, 스페이스 비엠, 서울' },
  ]);

  const handleInputChange = (e) => {
    setNewItemText(e.target.value);
  };

  const handleAddItem = () => {
    if (newItemText.trim() !== '') {
      const newItem = {
        color: '#171de5',
        children: newItemText,
      };

      // axios.post(`${process.env.BACKEND_URL}/timeline`, newItem)
      //   .then((response) => {
      //     setTimelineItems((prevItems) => [...prevItems, response.data]);
      //   })
      //   .catch((error) => {
      //     console.error('Error:', error);
      //   });

      console.log(newItem)

      setTimelineItems((prevItems) => [...prevItems, newItem]);
      setNewItemText('');
      setIsEditMode(false);
    }
  };

  const moveItem = (fromIndex, toIndex) => {
    const updatedTimelineItems = [...timelineItems];
    const [movedItem] = updatedTimelineItems.splice(fromIndex, 1);
    updatedTimelineItems.splice(toIndex, 0, movedItem);
    setTimelineItems(updatedTimelineItems);
  };

  return (
    <div className={styles.container}>
      <div className={styles.content_box}>
        <div className={styles.filmography} style={{ maxWidth: '600px', margin: 'auto', textAlign: 'center' }}>
          <div className={styles.slider_container}><Slider1 /></div>
        </div>
        <div className={styles.history}>
          <div className={styles.title_btn}>
            <div className={styles.title}>🎨 작가 이력</div>
            {/* {isEditMode ? (
              <button className={styles.edit} onClick={() => setIsEditMode(false)} style={{ fontFamily: 'Gowun Dodum' }}>
                완료
              </button>
            ) : (
              <button className={styles.edit} onClick={() => setIsEditMode(true)} style={{ fontFamily: 'Gowun Dodum' }}>
                수정하기
              </button>
            )} */}
          </div>
          {isEditMode && (
            <>
              <div className={styles.add_element}>
                <input
                  type="text"
                  value={newItemText}
                  onChange={handleInputChange}
                  placeholder="항목 추가"
                  className={styles.add_input}
                />
                <button onClick={handleAddItem} style={{ border:'#171de5', borderRadius: '8px', fontFamily: 'Gowun Dodum' }}>추가하기</button>
              </div>
              <DndProvider backend={HTML5Backend}>
                <div className={styles.history_content} style={{ fontFamily: 'Gowun Dodum' }}>
                  {timelineItems.map((item, index) => (
                    <DraggableTimelineItem key={index} item={item} index={index} moveItem={moveItem} />
                  ))}
                </div>
              </DndProvider>
            </>
          )}
          {!isEditMode && (
            <Timeline className={styles.history_content} items={timelineItems} />
          )}
        </div>
      </div>
    </div>
  );
};

export default ArtistPortfolio;