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
// import styles from './Navbar.module.scss';
import {ButtonContainer} from './ButtonContainer';
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
  const showSidebar = () => {console.log('퍄퍄'); setSidebar(!sidebar);}
  

  const contextValue = useContext(IconContext);
  const color = contextValue ? contextValue.color : '#fff';

  // const [isClosed, setIsClosed] = useState(false);

  // const handleToggle = () => {
  //   setIsClosed(!isClosed);
  // };

  return (
    <div style={{ display: 'flex', justifyContent: 'space-between'}}>
        <div className='navbar'>
          <div className='menu-bars'>
            <FaIcons.FaBars onClick={showSidebar} />
          </div>
        </div>
        <nav className={sidebar ? 'nav-menu active' : 'nav-menu'}>
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
          </ul>
        </nav>






    {/* <nav className={styles.navbar} style={{ marginBottom: '10px', display: 'flex', alignItems: 'center' }}> */}
    
        
        
        
        {/* <Sidebar />
        <div style={{ flex: '1', display: 'flex'}}>
        <Link href='/'>
        <div className={`${styles.logo} ${styles.center}`} />
        </Link>
        </div>
      <ButtonContainer /> */}
    
     {/* </nav> */}


    <div style={{ right: '20px', display: 'flex', alignItems: 'center', justifyContent: 'flex-end' }}><ButtonContainer /></div>
    

    </div>
  );
};

export default Navbar;




