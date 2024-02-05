import Image from "next/image";
// import getReviews, { ReviewsParams } from "../actions/getReviews";
import Container from "@/components/Container";
import EmptyState from "@/components/EmptyState";
// import getCurrentUser from "../actions/getCurrentUser";
// import FloatingButton from "@/components/FloatingButton";
// import ReviewCard from "@/components/review/ReviewCard";
// import Pagination from "@/components/Pagination";
import Carousel from '@/components/carousel/Carousel';
import styles from './review.module.scss'
import Navbar from "@/components/Navbar/Navbar";
import { get } from "http";
import axios from "axios";

// interface ReviewPageProps {
//   searchParams: ReviewsParams
// }

const CAROUSEL_IMAGES = [
  'https://img.freepik.com/free-photo/vivid-blurred-colorful-background_58702-2545.jpg',
  'https://img.freepik.com/premium-vector/abstract-pastel-color-background-with-pink-purple-gradient-effect-graphic-design-decoration_120819-463.jpg',
  'https://media.architecturaldigest.com/photos/6080a73d795a7b010f3dd2e0/2:1/w_2700,h_1350,c_limit/GettyImages-1213929929.jpg',
]

export default async function Review({searchParams}: any) {
  
  // const page = searchParams?.page;
  // const pageNum = typeof page === 'string' ? Number(page) : 1;


  const reviews = await axios.get('https://i10a207.p.ssafy.io/api/Review');
  // const currentUser = await getCurrentUser();
  return (
    <Container>
      <Navbar/>
      <Carousel
            carouselList={CAROUSEL_IMAGES}
          />
      {
        reviews.data?.length === 0
        ?
        <EmptyState showReset />
        :
        <>
          <div className={styles.Cards}>
            {reviews.data?.map((review:any) =>
            <div>
              {review.title}
            </div>
            )}
            
          </div>
        </>
      }
      <div className={styles.Pagination}>
      {/* <Pagination page={pageNum} totalItems={reviews.totalItems} /> */}
      </div>
      
      {/* <FloatingButton
      href="/review/upload">
        +
      </FloatingButton> */}
    </Container>
  );
}
