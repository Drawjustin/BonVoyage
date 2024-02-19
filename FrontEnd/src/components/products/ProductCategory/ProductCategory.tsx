import React from 'react';
import { IconType } from 'react-icons';
import styles from './ProductCategory.module.scss';

interface ProductCategoryProps {
  icon: IconType;
  label: string;
  description: string;
}

const ProductCategory = ({
  icon: Icon,
  label,
  description
}: ProductCategoryProps) => {
  return (
    <div className={styles.flex_container}>
      <Icon size={40} className={styles.icon} />
      <div className={styles.text_group}>
        <div className={styles.title}>{label}</div>
        <div className={styles.description}>{description}</div>
      </div>
    </div>
  );
}

export default ProductCategory;