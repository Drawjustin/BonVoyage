'use client'
import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import axios from 'axios';
import styles from './ProductDetailModifyPage.module.scss';

const EditProductPage = (props) => {
  const router = useRouter();
  const itemId = props.params.itemId; // 'itemId'는 [itemId].tsx 파일명에 매칭되는 동적 라우트

  const [productData, setProductData] = useState({
    itemName: '',
    artistId: '',
    itemSellPrice: '',
    explain: '',
    itemWidth: '',
    itemHeight: '',
  });
  const [itemName, setItemName] = useState()
  const [itemSellPrice, setItemSellPrice] = useState()
  const [explain, setExplain] = useState()
  const [itemWidth, setItemWidth] = useState()
  const [itemHeight, setItemHeight] = useState()

  console.log(itemId)


  useEffect(() => {
    // 해당 아이템의 정보를 불러와서 state에 설정
    const fetchProductData = async () => {
      try {
        const response = await axios.get(`https://i10a207.p.ssafy.io/api/item/${props.params.itemId}`);
        setProductData(response.data);
      } catch (error) {
        console.error('Error fetching product data:', error);
      }
    };

    if (itemId) {
      fetchProductData();
    }
  }, [itemId]);

  const handleNameChange = (e) => {
    setItemName(e.target.value)
  };

  const handleArtistIdChange = (e) => {
    setArtistId(e.target.value)
  };

  const handleItemSellPriceChange = (e) => {
    setItemSellPrice(e.target.value)
  };

  const handleExplainChange = (e) => {
    setExplain(e.target.value)
  };

  const handleItemWidthChange = (e) => {
    setItemWidth(e.target.value)
  };

  const handleItemHeightChange = (e) => {
    setItemHeight(e.target.value)
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const sendData = {
   itemName: itemName,
    artistId: productData.artistId,
    itemSellPrice: itemSellPrice,
    explain: explain,
    itemWidth: itemWidth,
    itemHeight: itemHeight,

    }

    console.log(sendData);

    try {
      // 수정된 정보를 서버로 전송
      await axios.put(`https://i10a207.p.ssafy.io/api/item/${itemId}`, sendData, 
      {headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      }});
      

      // console.log(response)
      // 수정 후 상세 페이지로 이동
      router.push(`/ProductDetailPage/${itemId}`);
    } catch (error) {
      console.error('Error updating product:', error);
    }
  };

  return (
    <div className={styles.all}>
      <h1>판매 작품 정보 수정</h1>
      <form onSubmit={handleSubmit}>
        <div className={styles.blank}>
          <label>
            작품명 <input type="text" name="name" onChange={handleNameChange} value={itemName}/>
          </label>
        </div>


        <div className={styles.blank}>
          <label>
            설명 <input type="text" name="description" value={explain} onChange={handleExplainChange} />
          </label>
        </div>

        <div className={styles.blank}>
          <label>
            가로 <input type="number" name="widthInput" value={itemWidth} onChange={handleItemWidthChange} />
          </label>
        </div>

        <div className={styles.blank}>
          <label>
            세로 <input type="number" name="heightInput" value={itemHeight} onChange={handleItemHeightChange} />
          </label>
        </div>

        <div className={styles.blank}>
          <label>
            가격 <input type="number" name="price" value={itemSellPrice} onChange={handleItemSellPriceChange} />
          </label>
        </div>

        <button style={{ marginTop: '2vh', display: 'flex', alignContent: 'end'}} type="submit">수정 완료</button>
      </form>
    </div>
  );
};

export default EditProductPage;
