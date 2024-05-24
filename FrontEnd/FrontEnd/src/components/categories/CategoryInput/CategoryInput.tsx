// CategoryInput.jsx

import React from 'react';
import { IconType } from 'react-icons';
import styles from './CategoryInput.module.scss';

interface CategoryInputProps {
  icon: IconType;
  label: string;
  selected?: boolean;
  path: string;
  onClick: (value: string) => void;
}

const CategoryInput = ({
  icon: Icon,
  label,
  selected,
  onClick,
  path
}: CategoryInputProps) => {
  return (
    <div
      onClick={() => onClick(path)}
      className={
          selected ? styles.toSelect : styles.toSelect_selected
      }
    >
      <Icon size={30} />
      <div className={styles.font_semibold}>
        {label}
      </div>
    </div>
  );
};

export default CategoryInput;
