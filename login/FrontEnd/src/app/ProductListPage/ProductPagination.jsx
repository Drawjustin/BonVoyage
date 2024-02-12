'use client'
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import EmptyState from '@/components/EmptyState/EmptyState';
import styles from './ProductListPage.module.scss'
import getCurrentUser from '@/app/actions/getCurrentUser';
import ProductCard from '@/components/products/ProductCard/ProductCard'
import Pagination from '@/components/Pagination/Pagination';
import {PRODUCTS_PER_PAGE} from '../../constants';
import { useSearchParams } from 'next/navigation';
import './m1.jpg';
import './m2.jpg';
import './m3.jpg';
import './m4.jpg';


const ProductPagination = ({PageLink}) => {
  
  const currentUser = getCurrentUser();
  const [totalItems, setTotalItems] = useState(0);
  const [Sort, setSortBy] = useState('Like');
  const [currentPage, setCurrentPage] = useState(1);
  const [products, setProducts] = useState([]);
  const searchParams = useSearchParams();
  const [page, setProductId] = useState('');


  const dummyData = [
    {
      id: 1,
      title: "별이 빛나는 밤",
      image: "./m1.jpg",
      price: 19000,
    },
    {
      id: 2,
      title: "배",
      image: "./m2.jpg",
      price: 240000,
    },
    {
      id: 3,
      title: "해바라기",
      image: "./m3.jpg",
      price: 650000,
    },
    {
      id: 4,
      title: "숲",
      image: "./m4.jpg",
      price: 14000,
    },
  ];


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
        // const response = await axios.get('https://i10a207.p.ssafy.io/api/item', Sort);

        // setProducts(response.data);
        // setTotalItems(response.data.length);

        setProducts(dummyData);
        setTotalItems(dummyData.length);

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
            <div className={styles.toggle_container}>
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
        <div className="container" style={{ marginTop: '10px' }}>
          <h1 style={{ color: '#f1efee', textAlign: 'left' }}>Products</h1>

          {Array.isArray(products) && products.length > 0 ? (
            Sort === 'New' ? (
            <div className={styles.grid}>
            {products.slice((currentPage - 1) * PRODUCTS_PER_PAGE, currentPage * PRODUCTS_PER_PAGE).map((product) => (
                    <ProductCard
                    currentUser={currentUser}
                    key={product.itemSeq}
                    data={product}
                    />
                    ))}
              
            </div>
            )
            :
            (
            <div className={styles.grid}>

               {products.slice((currentPage - 1) * PRODUCTS_PER_PAGE, currentPage * PRODUCTS_PER_PAGE).map((product) => (
                <ProductCard
                  currentUser={currentUser}
                  key={product.itemSeq}
                  data={product}
                />
              ))}
            </div>
            )
          ) : (
            <EmptyState showReset />
          )}
        </div>
        <div>
        
          <Pagination  currentPage={parseInt(page) > 0 ? parseInt(page) : currentPage} totalItems={totalItems} itemCountPerPage={PRODUCTS_PER_PAGE} pageCount={4}
          onPageChange={handlePageChange}/>

        </div>
        </>
        
    );
};

export default ProductPagination;