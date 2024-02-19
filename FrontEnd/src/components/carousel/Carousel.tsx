'use client'
import { TouchEventHandler, useEffect, useRef, useState } from 'react'

import { SlArrowLeft, SlArrowRight } from 'react-icons/sl'
import styles from './carousel.module.scss'

interface CarouselProps {
  carouselList: string[]
}

let touchStartX: number
let touchEndX: number

const Carousel = ({ carouselList }: CarouselProps) => {
  const [currIndex, setCurrIndex] = useState(1)
  const [currList, setCurrList] = useState<string[]>()

  const carouselRef = useRef<HTMLUListElement>(null)

  useEffect(() => {
    if (carouselList.length !== 0) {
      const startData = carouselList[0]
      const endData = carouselList[carouselList.length - 1]
      const newList = [endData, ...carouselList, startData]

      setCurrList(newList)
    }
  }, [carouselList])

  useEffect(() => {
    if (carouselRef.current !== null) {
      carouselRef.current.style.transform = `translateX(-${currIndex}00%)`
    }
  }, [currIndex])

  const moveToNthSlide = (index: number) => {
    setTimeout(() => {
      setCurrIndex(index)
      if (carouselRef.current !== null) {
        carouselRef.current.style.transition = ''
      }
    }, 500)
  }

  const handleSwipe = (direction: number) => {
    const newIndex = currIndex + direction

    if (newIndex === carouselList.length + 1) {
      moveToNthSlide(1)
    } else if (newIndex === 0) {
      moveToNthSlide(carouselList.length)
    }

    setCurrIndex((prev) => prev + direction)
    if (carouselRef.current !== null) {
      carouselRef.current.style.transition = 'all 0.5s ease-in-out'
    }
  }

  const handleTouchStart: TouchEventHandler<HTMLDivElement> = (e) => {
    touchStartX = e.nativeEvent.touches[0].clientX
  }

  const handleTouchMove: TouchEventHandler<HTMLDivElement> = (e) => {
    const currTouchX = e.nativeEvent.changedTouches[0].clientX

    if (carouselRef.current !== null) {
      carouselRef.current.style.transform = `translateX(calc(-${currIndex}00% - ${
        (touchStartX - currTouchX) * 2 || 0
      }px))`
    }
  }

  const handleTouchEnd: TouchEventHandler<HTMLDivElement> = (e) => {
    touchEndX = e.nativeEvent.changedTouches[0].clientX

    if (touchStartX >= touchEndX) {
      handleSwipe(1)
    } else {
      handleSwipe(-1)
    }
  }

  return (
    <div className={styles.container}>
      <div
        className={styles.carouselWrapper}
        onTouchStart={handleTouchStart}
        onTouchMove={handleTouchMove}
        onTouchEnd={handleTouchEnd}
      >
        <button type='button' className={styles.swipeLeft} onClick={() => handleSwipe(-1)}>
          <SlArrowLeft />
        </button>
        <button type='button' className={styles.swipeRight} onClick={() => handleSwipe(1)}>
        <SlArrowRight />
        </button>
        <ul className={styles.carousel} ref={carouselRef}>
          {currList?.map((image, idx) => {
            const key = `${image}-${idx}`

            return (
              <li key={key} className={styles.carouselItem}>
                <img src={image} alt='carousel-img' />
              </li>
            )
          })}
        </ul>
      </div>
    </div>
  )
}

export default Carousel