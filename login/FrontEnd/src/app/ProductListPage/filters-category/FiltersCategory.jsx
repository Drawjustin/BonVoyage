import React from 'react'
import styles from './FiltersCategory.module.scss';
import CategoryTab from './categories-tab/CategoryTab';
import { CategoriesName } from '../../../store/categories/categories.type';

const FiltersCategory = () => {
  return (
    <div className={styles.filter_category}>
            <CategoryTab text={"모두"} categoryName={CategoriesName.All} />
            <CategoryTab text={"판매상품"} categoryName={CategoriesName.General} />
            <CategoryTab text={"경매상품"} categoryName={CategoriesName.Artist} />
        </div>
  )
}

export default FiltersCategory