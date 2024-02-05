import React from 'react';
import Link from 'next/link';

interface PaginationProps {
  page: number;
  totalPages: number;
}

const Pagination: React.FC<PaginationProps> = ({ page, totalPages }) => {
  const prevPage = Math.max(page - 1, 1);
  const nextPage = Math.min(page + 1, totalPages);

  return (
    <div>
      <Link href={`/?page=${prevPage}`}>
        <a>{'<'}</a>
      </Link>

      {[...Array(totalPages)].map((_, i) => {
        const pageNumber = i + 1;

        return (
          <Link key={pageNumber} href={`/?page=${pageNumber}`}>
            <a style={{ fontWeight: pageNumber === page ? 'bold' : 'normal' }}>
              {pageNumber}
            </a>
          </Link>
        );
      })}

      <Link href={`/?page=${nextPage}`}>
        <a>{'>'}</a>
      </Link>
    </div>
  );
};

export default Pagination;
