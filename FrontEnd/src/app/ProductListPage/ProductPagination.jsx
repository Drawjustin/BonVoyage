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
  
  const ImageList = [
    'https://seoartgallery.com/wp-content/uploads/2016/07/828px-Vincent_Van_Gogh_-_Three_Sunflowers_F453-510x631.jpg',
    'https://i.pinimg.com/736x/97/95/ae/9795ae9009f61d5e1b01d78ec5cae030.jpg',
    'https://www.m-i.kr/news/photo/201703/297262_166384_5948.jpg',
    'https://auctiondaily.com/wp-content/uploads/2022/10/Korea-News-Breeze-Art-Fair-Opens-Where-You-Can-Meet-New-Korean-Artists-1.webp',
    'https://m.pressian.com/_resources/10/2021/09/29/2021092916105639554_l.jpg',
    'https://wimg.mk.co.kr/meet/neds/2022/03/image_readtop_2022_231921_16471547574973113.jpg',
    'https://artrie.com/HyAdmin/naverEditor/upload/1697258735.jpg',
    'https://post-phinf.pstatic.net/MjAyMjA2MjBfMjY4/MDAxNjU1NjkxMDc3NTUz.yoLw0kBWOKguYWyds9cQoQp4IZAXl_wMoxDE4XHGuvcg.eSJw4ShyB8o13YuE-AZ0Lxh42O-rSxtgploWvfO9mCYg.JPEG/20220601_141155.jpg?type=w800_q75',
    'https://image.newdaily.co.kr/site/data/img/2019/03/18/2019031800058_0.jpg',
    'https://og-data.s3.amazonaws.com/media/exhibitions/image/4969/ei_4969.jpg',
    'https://www.yeongnam.com/mnt/file/201808/20180815.010180744080001i1.jpg',
    'https://www.medisobizanews.com/news/photo/202303/105593_95467_1744.jpg',
    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4EpBShyIrPqYQKr2qz4aUbB57tPVMBUozQBLhHt4S8KpkLnBa9txIVo4FH91hLdvEw1k&usqp=CAU',
    'https://cdn.imweb.me/thumbnail/20221118/5b7fe48c48774.png',
    'https://lh3.googleusercontent.com/proxy/_vt2LnuQz14aX_GXXhd5IB3mRv-WQVzXGqjJaBZJb2n4LhjJ_VpS2oQ5GosF1XuqnoTv40wOhN5FCTyv1mH7ZOv24vwJXDQ3BbcHsnoH',
  ];
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
      const response = await axios.get('https://i10a207.p.ssafy.io/api/item', {
        params: {
          page: page,
          itemsPerPage: PRODUCTS_PER_PAGE,
        },
      });
      return response.data;
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
                    image={ImageList[(product.itemSeq)%15]}
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
                  image={ImageList[(product.itemSeq)%15]}
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