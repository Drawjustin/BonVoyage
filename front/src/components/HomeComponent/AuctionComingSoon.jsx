import React from 'react';
import styles from './HomeComponent.module.scss';


const AuctionComingSoon = () => {
  
  const artworks = [
    { id: 1, title: '모나리자', amount: '10억 원', description: '눈썹이 없는 것이 매우 매력적인 작품', specs: '50x70cm', artist: '레오나르도 다빈치', otherarts: '최후의 만찬'},
    { id: 2, title: '해바라기', amount: '30000000 원', description: '저희 어머니가 꽃을 좋아해서요..', specs: '30x60cm', artist: '반 고흐', otherarts: '자화상'},
    { id: 3, title: '절규', amount: '1억 50000000 원', description: '요즘 내 상황을 그려봤어요.', specs: '500x700cm', artist: '뭉크',  otherarts: '뭉키'},
    
  ];

  const [isExpaned, setIsExpanded] = useState(false);
  
  const handleToggle = () => {
    setIsExpanded(!isExpaned);
  };

  return (
    <div className={`component ${isExpanded ? 'expanded' : ''}`} onClick={handleToggle}>
    </div>
  );
};




export default AuctionComingSoon;
