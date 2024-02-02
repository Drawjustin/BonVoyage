import Link from 'next/link';
import React from 'react'
import { IconType } from 'react-icons'
import styles from './CategoryBox.module.scss'

interface CategoryBoxProps {
    icon: IconType;
    label: string;
    path: string;
    selected?: boolean;
}

const CategoryBox = ({
    icon: Icon,
    label,
    path,
    selected
}: CategoryBoxProps) => {
  return (
    <Link href={`/ProductListPage/?category=${path}`}
    className={selected ? styles.box_selected : styles.box }
    >
        <Icon size={26} />
        <div>
            {label}
        </div>
    </Link>
    
  )
}

export default CategoryBox