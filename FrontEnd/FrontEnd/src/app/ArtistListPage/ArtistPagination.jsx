'use client'
import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import EmptyState from '@/components/EmptyState/EmptyState';
import styles from './ArtistListPage.module.scss'
import getCurrentUser from '@/app/actions/getCurrentUser';
import ArtistCard from '@/components/Artists/ArtistCard/ArtistCard'
import Pagination from '@/components/Pagination/Pagination';
import {PRODUCTS_PER_PAGE} from '../../constants';
import { useSearchParams } from 'next/navigation';


const ArtistPagination = ({PageLink}) => {
  
  const ImageList = [
    'https://img.freepik.com/free-photo/portrait-of-young-woman-with-natural-make-up_23-2149084942.jpg?size=626&ext=jpg',
    'https://img.freepik.com/free-photo/asian-man-s-portrait-isolated-over-green-studio-background-with-copyspace_155003-46171.jpg?size=626&ext=jpg',
    'https://img.freepik.com/free-photo/confident-and-stylish-asian-woman-cross-arms-on-chest-and-smiling-standing-over-blue-background_1258-76001.jpg?size=626&ext=jpg',
    'https://img.freepik.com/free-photo/beautiful-cheerful-woman-sitting-by-a-white-wall_53876-102046.jpg?size=626&ext=jpg',
    'https://img.freepik.com/free-photo/lifestyle-beauty-and-fashion-people-emotions-concept-skeptical-and-judgemental-asian-female-office-manager-looking-picky-smirk-and-pouting-dissatisfied-cross-arms-chest_1258-59377.jpg?size=626&ext=jpg',
    'https://img.freepik.com/free-photo/portrait-of-happy-asian-woman-smiling-posing-confident-cross-arms-on-chest-standing-against-studio-background_1258-89269.jpg?size=626&ext=jpg&ga=GA1.2.2034595474.1707977958&semt=sph',
    'https://img.freepik.com/free-photo/front-view-of-man-wearing-white-top_23-2148364882.jpg?size=626&ext=jpg',
    'https://img.freepik.com/free-photo/closeup-portrait-of-handsome-stylish-asian-hipster-man-with-earring-smiling-pleased-camera-looking-enthusiastic-and-healthy-student-searching-job-after-graduation-white-background_176420-52617.jpg?size=626&ext=jpg&ga=GA1.2.2034595474.1707977958&semt=sph',
    'https://img.freepik.com/free-photo/portrait-of-volunteer-who-organized-donations-for-charity_23-2149230567.jpg?size=626&ext=jpg&ga=GA1.2.2034595474.1707977958&semt=sph',
    'https://img.freepik.com/free-photo/asian-woman-smiling-happy-face-portrait-close-up_53876-148130.jpg?size=626&ext=jpg&ga=GA1.2.2034595474.1707977958&semt=sph',
    'https://img.freepik.com/free-photo/portrait-of-countrywoman_52683-101177.jpg?size=626&ext=jpg&ga=GA1.2.2034595474.1707977958&semt=sph',
    'https://img.freepik.com/premium-photo/a-woman-with-a-short-haircut-and-a-short-sleeved-shirt-stands-in-front-of-a-building_80983-2924.jpg?size=626&ext=jpg&ga=GA1.2.2034595474.1707977958&semt=sph',
    'https://img.freepik.com/free-photo/portrait-of-young-and-beautiful-woman-gesticulating_273609-40467.jpg?size=626&ext=jpg&ga=GA1.2.2034595474.1707977958&semt=sph',
    'https://img.freepik.com/free-photo/medium-shot-night-portrait_23-2149005431.jpg?size=626&ext=jpg&ga=GA1.2.2034595474.1707977958&semt=sph',
    'https://img.freepik.com/free-photo/happy-asian-man-touching-chin-and-looking-at-camera_1262-18237.jpg?size=626&ext=jpg&ga=GA1.1.2034595474.1707977958&semt=sph',
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
      const response = axios.get('https://i10a207.p.ssafy.io/api/artists', {

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
          <h1 style={{ color: '#f1efee', textAlign: 'left', marginBottom: '10px', }}>작가 목록</h1>
          
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            <label>
              <button type='button'
                onClick={() => {setSortBy('Like')}}
                style={{ backgroundColor: Sort === 'Like' ? '#171de5' : '#f1efee',
                color: Sort==='Like' ? '#f1efee' : '#171de5' }}
                >
                인기순</button>
            </label>
            <br />

            <label>
              <button type='button'
                onClick={() => {setSortBy('New')}}
                style={{ backgroundColor: Sort === 'New' ? '#171de5' : '#f1efee',
                color: Sort==='New' ? '#f1efee' : '#171de5' }}
              >
              신인순
              </button>
            </label>
          
        <br />
      </div>
      </div>


        <div className="container" style={{ marginTop: '10px', marginLeft: '30vh' , width: '85%', alignItems: 'center' }}>
          

          {Array.isArray(fetchedData) && fetchedData.length > 0 ? (
            Sort === 'New' ? (
            <div className={styles.grid}>
            {fetchedData.slice((currentPage - 1) * PRODUCTS_PER_PAGE, currentPage * PRODUCTS_PER_PAGE).map((artist) => (
                    <ArtistCard
                    currentUser={currentUser}
                    key={artist.artistSeq}
                    data={artist}
                    image={ImageList[artist.artistSeq % 15]}
                    />
                    ))}
              
            </div>
            )
            :
            (
            <div className={styles.grid}>

               {fetchedData.slice((currentPage - 1) * PRODUCTS_PER_PAGE, currentPage * PRODUCTS_PER_PAGE).map((artist) => (
                <ArtistCard
                  currentUser={currentUser}
                  key={artist.seq}
                  data={artist}
                  image={ImageList[artist.seq % 15]}
                />
              ))}
            </div>
            )
          ) : (
            <EmptyState showReset />
          )}
        </div>
        <div style={{ marginTop: '5vh', textAlign: 'center', marginLeft: '30vh' }}>
        
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

export default ArtistPagination;