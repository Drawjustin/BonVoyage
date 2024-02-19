import React from 'react';
import Slider from 'react-slick';
import styled from "styled-components";
import styles from './Slider1.module.scss'

import img2 from './m2.jpg';
import img3 from './m3.jpeg';
import img4 from './m4.jpg';

const Slider1 = () => {
    const settings = {
        // slide: <Card />,
        dots: false,
        infinite: true,
        speed: 500,
        arrows: true,
        autoplay: false,
        autoplaySpeed: 2000,
        slidesToShow: 3,
        slidesToScroll: 1,
        centerMode: true,
        variableWidth: true,
        centerPadding: '0px',
        // draggable: false,
      };

      const StyledSlide = styled(Slider)`
        position: relative;
        margin-top: 30px;
        margin-bottom: -40px;
        width: 100%;
        // border: 1px solid black;
        
        .slick-list {
            margin-top: 20px;
            position: absolute;
            width: 100%;
            height: auto;
            margin: 0 auto;
            overflow: hidden;
            top: -30px;
            // border: 1px solid black;
        }
        
        .slick-slider {
            display: flex;
            width: 200px;
            // height: auto;
            // border: 1px solid black;
        }
        
        .slick-track {
            display: flex;
            // height: 100%;
        }
        
        .slick-dots {
            display: none !important;
        }
        
        .slick-arrow {
            // padding: 4px 6px;
            transform: translate(30px, 150px);
            color: black;
            border-radius: 3px;
            cursor: pointer;
        }
        
        .slick-prev {
            position: absolute;
            top: -80px;
            right: -800px;
            cursor: pointer;
            z-index: 100;
        }
        
        .slick-next {
            // position: absolute;
            top: -80px;
            // left: 450px;
            right: 40px;
            cursor: pointer;
            z-index: 100;
        }

        .slick-prev:before {
            color: #171de5;
        }

        .slick-next:before {
            color: #171de5;
        }
    `;

    return (
        <div className={styles.slide_container}>
            <StyledSlide {...settings}>
                <img src={'https://post-phinf.pstatic.net/MjAyMjA2MjBfMjY4/MDAxNjU1NjkxMDc3NTUz.yoLw0kBWOKguYWyds9cQoQp4IZAXl_wMoxDE4XHGuvcg.eSJw4ShyB8o13YuE-AZ0Lxh42O-rSxtgploWvfO9mCYg.JPEG/20220601_141155.jpg?type=w800_q75'} id="0" />
                <img src={'https://wimg.mk.co.kr/meet/neds/2022/03/image_readtop_2022_231921_16471547574973113.jpg'} id="1" />
                <img src={'https://image.newdaily.co.kr/site/data/img/2019/03/18/2019031800058_0.jpg'} id="2" />
                <img src={'https://www.yeongnam.com/mnt/file/201808/20180815.010180744080001i1.jpg'} id="3" />
                <img src={'https://www.medisobizanews.com/news/photo/202303/105593_95467_1744.jpg'} id="4" />
            </StyledSlide>
        </div>
    )
}

export default Slider1;