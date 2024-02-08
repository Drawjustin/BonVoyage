'use client'
import React,{ useState, useEffect } from "react";
import styles from "./Pagination.module.scss";
import { useRouter } from "next/navigation";

interface Props {
  totalItems: number;
  itemCountPerPage: number;
  pageCount: number;
  currentPage: number;
}

export default function Pagination({ totalItems, itemCountPerPage, pageCount, currentPage }: Props) {
  const totalPages = Math.ceil(totalItems / itemCountPerPage);
  const [start, setStart] = useState(1);
  const noPrev = start === 1;
  const noNext = start + pageCount - 1 >= totalPages;

  const navigate = useRouter();

  useEffect(() => {
    if (currentPage === start + pageCount) setStart((prev) => prev + pageCount);
    if (currentPage < start) setStart((prev) => prev - pageCount);
  }, [currentPage, pageCount, start]);


  return (
    <div className={styles.wrapper}>
      <ul className={styles["pagination-list"]}>
        <li className={`${styles.move} ${noPrev && styles.invisible}`}>
          <button onClick={() => navigate.push(`?page=${start - 1}`)}>이전</button>
        </li>
        {[...Array(pageCount)].map((_, i) => (
          <>
            <li key={start + i}>
            <button
              className={`${styles.page} ${currentPage === start + i && styles.active}`}
              onClick={() => navigate.push(`?page=${start + i}`)}
            >
              {start + i}
            </button>
          </li>
          </>
          
        ))}
        <li className={`${styles.move} ${noNext && styles.invisible}`}>
          <button onClick={() => navigate.push(`?page=${start + pageCount}`)}>다음</button>
        </li>
      </ul>
    </div>
  );
}
