'use client'
import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import EmptyState from '@/components/EmptyState/EmptyState';
import styles from './AuctionListPage.module.scss'
import getCurrentUser from '@/app/actions/getCurrentUser';
import AuctionCard from '@/components/Auctions/AuctionCard/AuctionCard'
import Pagination from '@/components/Pagination/Pagination';
import {PRODUCTS_PER_PAGE} from '../../constants';
import { useSearchParams } from 'next/navigation';


const AuctionPagination = ({PageLink}) => {
  
  const ImageList = [
  'https://post-phinf.pstatic.net/20161019_250/1476869873132HGuVr_JPEG/101.cafe-terrace-place-du-forum-arles-1888%281%29.jpg?type=w800_q75',
  'https://i0.wp.com/cdn.tiqets.com/wordpress/blog/wp-content/uploads/2021/04/07092809/909px-Van_Gogh_-_Starry_Night_-_Google_Art_Project.jpg?resize=909%2C720&ssl=1',
  'https://img.hankyung.com/photo/202108/01.27153801.1.jpg',
  'https://file.mk.co.kr/meet/neds/2012/09/image_readtop_2012_558936_1346631627715607.jpg',
  'https://mblogthumb-phinf.pstatic.net/MjAyMzAzMDRfMjc3/MDAxNjc3OTA3MzQzODc0.Ab6w45CixyH335WqVp3G2tLIkUg6W1tgDVIXPUC7OtAg.tZNEeUlTIfvnXF8J22tEAERJZW-JAoqGyhD8bKFc3AEg.PNG.yogocode/SE-d12e618f-16cd-4461-9ef6-124515bc1fc4.png?type=w800',
  'https://cdn.namdonews.com/news/photo/202106/608469_229017_5028.jpg',
  'https://newsprime.co.kr/data/photos/news/photo/200710/23532-2-9325.jpg'
  ]

  const currentUser = getCurrentUser();
  const [totalItems, setTotalItems] = useState(0);
  const [Sort, setSortBy] = useState('Like');
  const [currentPage, setCurrentPage] = useState(1);
  // const [products, setProducts] = useState([]);
  const searchParams = useSearchParams();
  const [page, setProductId] = useState('');
  const [fetchedData, setFetchedData] = useState([]);
  
  // 서버에서 데이터를 가져오는 함수
  const fetchDataFromBackend = async (page) => {
    try {
      const response = axios.get('https://i10a207.p.ssafy.io/api/auction', {

      });
      return (await response).data;
    } catch (error) {
      console.error('Error fetching products from backend:', error);
      throw error;
    }
  };

  useEffect(() => {
    if (typeof window !== 'undefined') {
      window.scrollTo(0, 0);
    }
  
    const fetchData = async () => {
      try {
        const fetchedData = await fetchDataFromBackend(currentPage);
        
        if (Array.isArray(fetchedData)) {
          setFetchedData(fetchedData);
          console.log(fetchedData);
          setTotalItems(fetchedData.length);
        } else {
          console.error('Error: fetchedData is not an array');
        }
      } catch (error) {
        console.error('Error fetching products:', error);
        // 에러 핸들링을 추가하거나 필요에 따라 예외 처리를 진행하세요.
      }
    };
  
    fetchData();
  }, [currentPage]);

  useEffect(() => {
    const id = searchParams.get("page");
    if (id) {
      setProductId(id);
    }

    if (!id) {
      setCurrentPage(1);
    }
    else {
      setCurrentPage(id);
    }
    
  }, [searchParams])

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
  };

  

    return (
        <>
          <div className={styles.toggle_container} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginLeft: '24vh', width: '90%' }}>
      </div>


        <div className="container" style={{ marginTop: '10px', marginLeft: '13vh' , width: '85%', alignItems: 'center' }}>
          

          {Array.isArray(fetchedData) && fetchedData.length > 0 ? (
            
            <div className={styles.grid}>

               {fetchedData.slice((currentPage - 1) * PRODUCTS_PER_PAGE, currentPage * PRODUCTS_PER_PAGE).map((auction) => (
                <AuctionCard
                  currentUser={currentUser}
                  key={auction.itemSeq}
                  data={auction}
                  image={ImageList[(auction.itemSeq)%7]}
                />
              ))}
            </div>
            
          ) : (
            <EmptyState showReset />
          )}
        </div>
        <div style={{ marginTop: '5vh', textAlign: 'center', marginLeft: '10vh' }}>
        
          <Pagination 
            currentPage={parseInt(page) > 0 ? parseInt(page) : currentPage} 
            totalItems={totalItems} 
            itemCountPerPage={PRODUCTS_PER_PAGE} 
            pageCount={Math.ceil(totalItems / PRODUCTS_PER_PAGE)}
            onPageChange={handlePageChange}/>

        </div>
        </>
        
    );
};

export default AuctionPagination;