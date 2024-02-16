'use client'
import React, { useEffect } from 'react'
import { useAppDispatch, useAppSelector } from '../../../hooks/redux'
import { fetchProducts } from '../../../store/products/products.slice';
import CardItem from './card-item/CardItem';
import styles from './CardList.module.scss';
import m1 from '../../m1.jpg';
import m2 from '../../m2.jpg';
import m3 from '../../m3.jpg';
import m4 from '../../m4.jpg';

const CardList = () => {

    const dispatch = useAppDispatch();
    const { products, isLoading } = useAppSelector(state => state.productsSlice)
    const category = useAppSelector(state => state.categoriesSlice)

    useEffect(() => {
        dispatch(fetchProducts(category?.toLowerCase()));
    }, [category])

    const dummyData = [
        {
          id: 1,
          title: "별이 빛나는 밤",
          image: m1,
          price: 19000,
        },
        {
          id: 2,
          title: "배",
          image: m2,
          price: 240000,
        },
        {
          id: 3,
          title: "해바라기",
          image: m3,
          price: 650000,
        },
        {
          id: 4,
          title: "숲",
          image: m4,
          price: 14000,
        },
      ];


    if (isLoading) return <h1>Loading..</h1>

    return (
        <ul className={styles.card_list}>
            {/* {products.map(product => <CardItem key={product.id} item={product} />)} */}
            {dummyData.map((item) => (
                <CardItem key={item.id} item={item} />
            ))}
        
        </ul>
    )
}

export default CardList