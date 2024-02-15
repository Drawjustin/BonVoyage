import React, { useEffect, useState, useRef } from "react";
import styles from "./IslandHtmlOverlay.module.scss";
import { Link } from "react-router-dom";
import { useRouter } from 'next/navigation';
import Img1 from "./img/review.png"
import Img2 from "./img/product.png"
import Img3 from "./img/auction.png"
import Img4 from "./img/artist.png"

const IslandHtmlOverlay = ({ selectedIsland } ) => {
  const [showOverlay, setShowOverlay] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      setShowOverlay(true);
    }, 800);
  }, [selectedIsland]);

  const navigate = useRouter();

  // 작품 리뷰
  const handleIslandClick1 = () => {
    navigate.push('/ReviewPage')
  };

  // 판매 작품
  const handleIslandClick2 = () => {
    navigate.push('/ProductListPage')
  };

  // 경매 작품
  const handleIslandClick3 = () => {
    navigate.push('/AuctionListPage')
  };

  // 작가 목록
  const handleIslandClick4 = () => {
    navigate.push('/ArtistListPage')
  };

  const renderContent = () => {
    switch (selectedIsland) {
      case 1:
        return (
          <div className={styles.card}>
            <div className={styles.icon_container}>
              <div className={styles.icon}>
                <img src={Img1} alt="Review" />
              </div>
            </div>
          <div className={styles.title}>작품 리뷰</div>
          <div className={styles.content}>
            <div>작품 리뷰를 만나보세요!</div>
          </div>
          <button onClick={handleIslandClick1} className={styles.btn}>바로 가기</button>
          </div>
        );
      case 2:
        return (
          <div className={styles.card}>
            <div className={styles.icon_container}>
              <div className={styles.icon}>
                <img src={Img2} alt="Product" className={styles.img2}/>
              </div>
            </div>
          <div className={styles.title}>판매 작품</div>
          <div className={styles.content}>
            <div>판매 작품을 둘러보고 작품을 구매해보세요!</div>
          </div>
          <button onClick={handleIslandClick2} className={styles.btn}>바로 가기</button>
          </div>
        );
      case 3:
        return (
          <div className={styles.card}>
            <div className={styles.icon_container}>
              <div className={styles.icon}>
                <img src={Img3} alt="Auction" className={styles.img3}/>
              </div>
            </div>
          <div className={styles.title}>경매 작품</div>
          <div className={styles.content}>
            <div>경매 작품을 둘러보고<br></br>작품 경매에 참여해보세요!</div>
          </div>
          <button onClick={handleIslandClick3} className={styles.btn}>바로 가기</button>
          </div>
        );
      case 4:
        return (
          <div className={styles.card}>
            <div className={styles.icon_container}>
              <div className={styles.icon}>
                <img src={Img4} alt="Review" />
              </div>
            </div>
          <div className={styles.title}>작가 목록</div>
          <div className={styles.content}>
            <div>작가들을 둘러보고<br></br>소통의 기회를 가져보세요!</div>
          </div>
          <button onClick={handleIslandClick4} className={styles.btn}>바로 가기</button>
          </div>
        );
      default:
        return null;
    }
  };

  return (
    <div className={`${styles.overlay} ${showOverlay && styles.show}`}>
      <div className={styles.overlayContent}>
        {renderContent()}
      </div>
    </div>
  );
};

export default IslandHtmlOverlay;