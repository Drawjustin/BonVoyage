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
// import m1 from './m1.jpg';
// import m2 from './m2.jpg';
// import m3 from './m3.jpg';
// import m4 from './m4.jpg';


const ProductPagination = ({PageLink}) => {
  
  const currentUser = getCurrentUser();
  const [totalItems, setTotalItems] = useState(0);
  const [Sort, setSortBy] = useState('Like');
  const [currentPage, setCurrentPage] = useState(1);
  // const [products, setProducts] = useState([]);
  const searchParams = useSearchParams();
  const [page, setProductId] = useState('');
  const [fetchedData, setFetchedData] = useState([]);
  const navigation = useRouter();
  
  // 서버에서 데이터를 가져오는 함수
  const fetchDataFromBackend = async (page) => {
    try {
      const response = axios.get('https://i10a207.p.ssafy.io/api/item', {

      });
      return (await response).data;
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
    setCurrentPage(id);
  }, [searchParams])

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
            onchange={handlePageChange}/>

        </div>
        </>
        
    );
};

export default ProductPagination;