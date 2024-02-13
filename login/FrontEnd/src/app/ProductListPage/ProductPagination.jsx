'use client'
import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import EmptyState from '@/components/EmptyState/EmptyState';
import styles from './ProductListPage.module.scss'
import getCurrentUser from '@/app/actions/getCurrentUser';
import ProductCard from '@/components/products/ProductCard/ProductCard'
import Pagination from '@/components/Pagination/Pagination';
import {PRODUCTS_PER_PAGE} from '../../constants';
import { useSearchParams } from 'next/navigation';
import m1 from './m1.jpg';
import m2 from './m2.jpg';
import m3 from './m3.jpg';
import m4 from './m4.jpg';


const ProductPagination = ({PageLink}) => {
  
  const currentUser = getCurrentUser();
  const [totalItems, setTotalItems] = useState(0);
  const [Sort, setSortBy] = useState('Like');
  const [currentPage, setCurrentPage] = useState(1);
  // const [products, setProducts] = useState([]);
  const searchParams = useSearchParams();
  const [page, setProductId] = useState('');
  const [fetchedData, setFetchedData] = useState([]);

  // 여기에 DB에서 데이터

  // const dummyData = [
  //   {
  //     id: 1,
  //     title: "별이 빛나는 밤",
  //     image: m1,
  //     price: 19000,
  //   },
  //   {
  //     id: 2,
  //     title: "배",
  //     image: m2,
  //     price: 240000,
  //   },
  //   {
    //     id: 3,
    //     title: "해바라기",
    //     image: m3,
    //     price: 650000,
    //   },
    //   {
  //     id: 4,
  //     title: "숲",
  //     image: m4,
  //     price: 14000,
  //   },
  // ];
  
  // 서버에서 데이터를 가져오는 함수
  const fetchDataFromBackend = async (sortBy, page) => {
    try {
      const response = await axios.get('https://i10a207.p.ssafy.io/api/item', {
        params: {
          sort: sortBy,
          page: page,
        },
      });

      return response.data;
    } catch (error) {
      console.error('Error fetching products from backend:', error);
      throw error;
    }
  };
  
  useEffect(() => {
    const id = searchParams.get("page");
    if (id) {
      setProductId(id);
    }
  }, [searchParams])

  useEffect(() => {
    if (typeof window !== 'undefined'){
        window.scrollTo(0, 0);
    }

    
    const fetchData = async () => {
      try {
        // 이게 원래 코드
        // const response = await axios.get('https://i10a207.p.ssafy.io/api/item', Sort);
        const fetchedData = await fetchDataFromBackend(Sort, currentPage);
        

        setFetchedData(fetchedData);
        setTotalItems(fetchedData.length);
        // setProducts(response.data);
        // setTotalItems(response.data.length);

        // 가데이터 임시방편 출력c
        // setProducts(dummyData);
        // setTotalItems(dummyData.length);

      } catch (error) {
        console.error('Error fetching products:', error);
        throw error; // Rethrow the error to be handled elsewhere
      }
    };
  
    fetchData();
  }, [Sort, currentPage])

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
  };

  

    return (
        <>
          <div className={styles.toggle_container} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginLeft: '24vh', width: '90%' }}>
          <h1 style={{ color: '#f1efee', textAlign: 'left', marginBottom: '10px', }}>판매 작품</h1>
          
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
              최신순
              </button>
            </label>
          
        <br />
      </div>
      </div>


        <div className="container" style={{ marginTop: '10px', marginLeft: '30vh' , width: '85%', alignItems: 'center' }}>
          

          {Array.isArray(fetchedData) && fetchedData.length > 0 ? (
            Sort === 'New' ? (
            <div className={styles.grid}>
            {fetchedData.slice((currentPage - 1) * PRODUCTS_PER_PAGE, currentPage * PRODUCTS_PER_PAGE).map((product) => (
                    <ProductCard
                    currentUser={currentUser}
                    key={product.itemSeq}
                    data={product}
                    image={product.image}
                    />
                    ))}
              
            </div>
            )
            :
            (
            <div className={styles.grid}>

               {fetchedData.slice((currentPage - 1) * PRODUCTS_PER_PAGE, currentPage * PRODUCTS_PER_PAGE).map((product) => (
                <ProductCard
                  currentUser={currentUser}
                  key={product.itemSeq}
                  data={product}
                  image={product.image}
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

export default ProductPagination;