'use client'
import React, {useEffect, useState} from 'react';
import styles from '../HomeComponent.module.scss';
import one from './1.jpg';
import two from './2.jpg';
import three from './3.jpg';


const fadeRGBColor = (RGBColor) => {
  const valuesPart = RGBColor.split(/['()']/)[1];
  return 'rgba(' + valuesPart + ', 0.4)';
};

function AniEffect() {useEffect(() => {
  const items = [...document.querySelectorAll('.menu_item')];
  let color, test, selectedElem, prevSelectedElem;

  items.forEach((item) => {
    item.addEventListener('mouseenter', (e) => {
      color = getComputedStyle(e.target)['background_color'];
      document.body.style['background_color'] = fadeRGBColor(color);
    });

    item.onclick = (e) => {
      selectedElem = e.target;

      if (!selectedElem.classList.contains('menu_item')) {
        selectedElem = selectedElem.parentElement;
      }

      prevSelectedElem = document.body.querySelector('.selected');
      prevSelectedElem && prevSelectedElem.classList.remove('selected');

      selectedElem.classList.add('selected');
    };
  });
}, []);
}

const AuctionComingSoon = () => {
  
  const artworks = [
    { id: 1, src: one, title: '모나리자', amount: '10억 원', description: '눈썹이 없는 것이 매우 매력적인 작품', specs: '50x70cm', artist: '레오나르도 다빈치', otherarts: '최후의 만찬'},
    { id: 2, src: two, title: '해바라기', amount: '30000000 원', description: '저희 어머니가 꽃을 좋아해서요..', specs: '30x60cm', artist: '반 고흐', otherarts: '자화상'},
    { id: 3, src: three, title: '절규', amount: '1억 50000000 원', description: '요즘 내 상황을 그려봤어요.', specs: '500x700cm', artist: '뭉크',  otherarts: '뭉키'},
    
  ];

  const [selectedArtwork, setSelectedArtwork] = useState(null);
  
  const handleArtworkClick = (artwork) => {
    setSelectedArtwork(artwork);
  };


  useEffect(() => {
    const items = [...document.querySelectorAll('.menu-item')];
    let color, selectedElem, prevSelectedElem;

    const handleMouseEnter = (e) => {
      color = getComputedStyle(e.target)['background-color'];
      document.body.style['background-color'] = fadeRGBColor(color);
    };

    const handleItemClick = (e) => {
      selectedElem = e.target;

      if (!selectedElem.classList.contains('menu-item')) {
        selectedElem = selectedElem.parentElement;
      }

      prevSelectedElem = document.body.querySelector('.selected');
      prevSelectedElem && prevSelectedElem.classList.remove('selected');

      selectedElem.classList.add('selected');
    };

    items.forEach((item) => {
      item.addEventListener('mouseenter', handleMouseEnter);
      item.onclick = handleItemClick;
    });

    return () => {
      items.forEach((item) => {
        item.removeEventListener('mouseenter', handleMouseEnter);
        item.onclick = null;
      });
    };
  }, []);

  const fadeRGBColor = (RGBColor) => {
    const valuesPart = RGBColor.split(/['()']/)[1];
    return 'rgba(' + valuesPart + ', 0.4)';
  };

  
  return (
    <div>
    <h1 style={{ fontSize: '30px', fontWeight: 'bold', textAlign: 'left', color: '#f1efee' }}>경매 임박 작품</h1>
    <div className={`${styles.component} ${selectedArtwork ? styles.expanded : ''}`}>
        <div className={styles.artworkList}>
          {artworks.map((artwork) => (
            <img 
              key={artwork.id}
              src={artwork.src}
              alt={artwork.title}
              onClick={() => handleArtworkClick(artwork)}
            />
          ))}
        </div>
        {selectedArtwork && (
          <div className={styles.content} style={{ paddingBottom: '50px' }}>
            <h2>{selectedArtwork.title}</h2>
            <p>금액 <br />{selectedArtwork.amount}</p>
            <p>설명 <br />{selectedArtwork.description}</p>
            <p>스펙 <br />{selectedArtwork.specs}</p>
            <p>작가 <br /> {selectedArtwork.artist}</p>
            <p>다른 작품 <br />{selectedArtwork.otherarts}</p>
          </div>
        )}
    </div>

    </div>
    
  );
};




export default AuctionComingSoon;
