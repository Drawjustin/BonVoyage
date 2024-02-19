'use client'
import { useAppDispatch, useAppSelector } from '../../../../hooks/redux';
import { setActiveCategory } from '../../../../store/categories/categories.slice';
import styles from './CategoryTab.module.scss';


const CategoryTab = ({ text, categoryName }) => {
    const dispatch = useAppDispatch();
    const category = useAppSelector((state) => state.categoriesSlice);

    const getActiveCategory = () => {
        dispatch(setActiveCategory(categoryName))
    }

    return (
        <button
            className={
                categoryName === category
                    ? styles.active_category
                    : styles.category_button
            }
            onClick={getActiveCategory}
        >
            {text}
        </button>
    )
}

export default CategoryTab