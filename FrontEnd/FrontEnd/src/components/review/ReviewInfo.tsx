import React from 'react'
import Avatar from '../Avatar';
import { formatTime } from '@/helpers/dayjs';
import style from './ReviewInfo.module.scss';

// interface ReviewInfoProps {
//     user: User,
//     description: string,
//     createdAt: Date,
// }

const ReviewInfo = ({
    user,
    createdAt,
    description
}: any) => {
  return (
    <div>
        <div>
            <div className={style.Avatar}>
                <Avatar src={user?.image}/>
                <p>{user?.name}</p>
            </div>
            <div>
                {formatTime(createdAt)}
            </div>
        </div>
        <div>
            {description}
        </div>
    </div>
  )
}

export default ReviewInfo