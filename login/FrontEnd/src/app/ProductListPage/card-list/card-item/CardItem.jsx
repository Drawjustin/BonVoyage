'use client'
import React from 'react'
import { Link } from 'react-router-dom';
import { useAppDispatch, useAppSelector } from '../../../../hooks/redux';
import { addToCart } from '../../../../store/cart/cart.slice';
import styles from './CardItem.module.scss';
import '../../m1.jpg';
import '../../m2.jpg';
import '../../m3.jpg';
import '../../m4.jpg';


const CardItem = ({ item }) => {

  const { products } = useAppSelector(state => state.cartSlice);
  const productMatching = products.some((product) => product.id === item.id);
  const dispatch = useAppDispatch();

  const addItemToCart = () => {
    dispatch(addToCart(item));
  }

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


  const selectedDummyData = dummyData.find((dummyItem) => dummyItem.id === item.id);
  

  return (
    <li className={styles.card_item}>
      <Link to={`/product/${item.id}`}>
        <img
          src={require(`${selectedDummyData.image}`).default}
          width={"80%"}
          height={"200px"}
          alt="product card"
        />
      </Link>

      <h5>{item.title.substring(0, 15)}...</h5>

      <div>
        <button
          disabled={productMatching}
          onClick={() => !productMatching && addItemToCart()}
        >
          {productMatching ? "장바구니에 담긴 제품" : "장바구니에 담기"}
        </button>
        <p>{selectedDummyData.price} 원</p>
      </div>

    </li>
  )
}

export default CardItem