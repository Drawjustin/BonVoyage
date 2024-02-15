'use client'
import styles from './ArtistHomePage.module.scss'
import dynamic from 'next/dynamic';
// import getCurrentUser from '@/app/actions/getCurrentUser';
// import axios from 'axios';
// import { usePathname } from 'next/navigation';
import ArtistTalk from '../../../components/ArtistHomepage/ArtistTalk/ArtistTalk';
import ArtistAuctionCalendar from '../../../components/ArtistHomepage/ArtistAuctionCalendar/ArtistAuctionCalendar'

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import getCurrentUser from '@/app/actions/getCurrentUser';

const ArtistPortfolio = dynamic(() => import('../../../components/ArtistHomepage/ArtistPortfolio/ArtistPortfolio'), {ssr:false});
const ArtistReview = dynamic(() => import('../../../components/ArtistHomepage/ArtistReview/ArtistReview'), {ssr:false});
const ArtistProfile = dynamic(() => import('../../../components/ArtistHomepage/ArtistProfile/ArtistProfile'), {ssr:false});

const MAIN_DATA = [
  { id: 1, name: 'portfolio', text: '정보' },
  { id: 2, name: 'talk', text: '소통' },
  { id: 3, name: 'calender', text: '일정' },
  { id: 4, name: 'review', text: '리뷰' },
];

const ArtistHomePage = ({ params }: { params: { artistId: String } }) => {
  // const router = usePathname();
  // const pathname = usePathname();
  // const { artistId } = router; // artistId 가져오기

  // console.log(router)
  // console.log(params.artistId)
  const Id = params.artistId
  const [artistSeq, setArtistSeq] = useState<number | null>(null); // artist의 seq 값 상태 추가

  const [artist, setArtist] = useState<any>(null); // any 타입으로 임시 지정
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<any>(null); // any 타입으로 임시 지정

  useEffect(() => {
    const fetchData = async () => {
      try {
        const backendUrl = 'https://i10a207.p.ssafy.io/api';
        // const response = await axios.get(`${backendUrl}/artistMentions`);
        const response = await axios.get(`${backendUrl}/artists/${Id}`); // artistId를 이용하여 API 호출
        setArtist(response.data);
        setArtistSeq(response.data.seq);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    if (Id) { // artistId가 있을 때만 데이터를 가져오도록 처리
      fetchData();
    }
  }, [Id]);

  const currentUser = getCurrentUser();
  // const Userinfo = JSON.parse(currentUser)
  // const UserId = Userinfo.id

  // 작가 정보 받아오기

  // 카테고리
  const selectComponent: { [key: string]: JSX.Element } = {
    portfolio: <ArtistPortfolio />,
    talk: <ArtistTalk isArtist={currentUser} artistId={Id} artistSeq={artistSeq}/>,
    calender: <ArtistAuctionCalendar artistSeq={artistSeq} />,
    review: <ArtistReview />,
  }

  const [content, setContent] = useState(selectComponent.portfolio);
  const [btnActive, setBtnActive] = useState("portfolio");
  
  const handleClickButton = (e: React.MouseEvent<HTMLButtonElement>) => {
    const { name } = e.currentTarget;
    setContent(selectComponent[name]);
    setBtnActive(name);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  if (!artist) return <div>Artist not found</div>;

  return (
    <div style={{ marginTop: '3vh' }} className={styles.container}>
        <div className={styles.contents}>
          <div className={styles.profile}>
              <ArtistProfile artistId={Id}/>
          </div>
          <div className={styles.content}>
            <div className={styles.inner_content}>
              <div className={styles.title}>
                <div className={styles.selectbtn}>
                  {MAIN_DATA.map((data) => (
                    <button onClick={handleClickButton} name={data.name} key={data.id}
                    className={btnActive === data.name ? styles.activebtn : ""}>
                      {data.text}
                    </button>
                  ))}
                </div>
              </div>
            <div className={styles.page_content}>
              <div className={styles.page_box}>
                {content}
              </div>
            </div>
            </div>
          </div>
        </div>
      </div>
  );
};

export default ArtistHomePage;