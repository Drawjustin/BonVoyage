'use client'

import React, {useEffect, useState, useContext} from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
// import { Link } from 'react-router-dom';
import Link from 'next/link';
import { SidebarData } from './SidebarData';
import './newnavbar.css';
import { IconContext } from 'react-icons';
// import Link from 'next/link';
import styles from './Navbar.module.scss';
import {ButtonContainer} from './ButtonContainer';
import { useRouter } from 'next/navigation';
// import Sidebar from './Sidebar';

interface SidebarItem {
  title: string;
  path: string;
  icon: JSX.Element;
  cName: string;
}

const Navbar: React.FC = () => {
  const [sidebar, setSidebar] = useState(false);

  // const showSidebar = () => setSidebar(!sidebar);
  const showSidebar = () => {setSidebar(!sidebar);}
  const navigate = useRouter();

  const contextValue = useContext(IconContext);
  const color = contextValue ? contextValue.color : '#fff';

  // const [isClosed, setIsClosed] = useState(false);

  // const handleToggle = () => {
  //   setIsClosed(!isClosed);
  // };

  return (
    
    <div style={{ display: 'flex', justifyContent: 'space-between' }}>
       <div className={styles.logo_title} onClick={() => {navigate.push('/')}}>Bon Voyage</div>
        <div className='navbar'>
          <div className='menu-bars'>
            <FaIcons.FaBars onClick={showSidebar} />
          </div>
        </div>
        <nav className={sidebar ? 'nav-menu active' : 'nav-menu'} style={{ zIndex: 1000 }}>
          <ul className='nav-menu-items' onClick={showSidebar} style={{ display: 'flex', flexDirection: 'column'}}>
            <li className='navbar-toggle'>
              <Link href='#' className='menu-bars'>
                <AiIcons.AiOutlineClose />
              </Link>
            </li>
            {SidebarData.map((item: SidebarItem, index: number) => (
              <li key={index} className={item.cName}>
                <Link href={item.path}>
                  <div style={{ width: '100px' }}>
                    {item.icon} 
                    <span>   {item.title}</span>
                  </div>
                </Link>
              </li>
            ))}
          <div style={{ right: '20px', display: 'flex', alignItems: 'center', justifyContent: 'flex-end', position: 'absolute', bottom: '30px', marginLeft: '15px', color: '#f1efee', fontSize: '13px' }}>
              <p>Made by. A207</p>
              <p>이동훈 김성욱 김아린 박수민 유현종 이윤정</p>
          </div>
          </ul>
        </nav>

    <div style={{ right: '20px', display: 'flex', alignItems: 'center', justifyContent: 'flex-end' }}><ButtonContainer /></div>
    

    </div>
  );
};

export default Navbar;




