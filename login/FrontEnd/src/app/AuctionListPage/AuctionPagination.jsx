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
                  image={auction.image}
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