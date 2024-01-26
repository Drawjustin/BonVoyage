import Image from 'next/image'
import React from 'react'
import styles from './Avatar.module.scss'

interface AvatarProps {
    src: string | null;
}

const Avatar = ({src}:AvatarProps) => {
  return (
    <Image
        className={styles.avatar}
        height={30}
        width={30}
        alt="avatar"
        src={src || "https://via.placeholder.com/400x400?text=no+user+image"}
    />
  )
}

export default Avatar