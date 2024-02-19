import React from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';

export const SidebarData = [
    {
        title: 'Home',
        path: '/',
        icon: <AiIcons.AiFillHome />,
        cName: 'nav-text'
    },
    {
        title: '판매 작품',
        path: '/ProductListPage',
        icon: <FaIcons.FaCartPlus />,
        cName: 'nav-text'
    },
    {
        title: '경매 작품',
        path: '/AuctionListPage',
        icon: <FaIcons.FaCartPlus />,
        cName: 'nav-text'
    },
    {
        title: '작가',
        path: '/ArtistListPage',
        icon: <IoIcons.IoMdPeople/>,
        cName: 'nav-text'
    },
    {
        title: '리뷰',
        path: '/ReviewPage',
        icon: <FaIcons.FaEnvelopeOpenText />,
        cName: 'nav-text'
    },

]