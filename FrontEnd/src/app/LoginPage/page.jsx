'use client'
import React from 'react'
import Navbar from '@/components/Navbar/Navbar';
import styles from '@/components/InputLogin/InputLogin.module.scss'
import Link from 'next/link';
import { FaUser, FaLock } from 'react-icons/fa';
import { signIn } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import axios from 'axios';
import InputLogin from '@/components/InputLogin/InputLogin';
import { getProviders } from 'next-auth/react';

async function LoginPage() {
  
    return (
      
        
    <div>
      <div style={{ marginTop: '10vh' }}>
        <InputLogin/>
      </div>
    </div>





    )
      }

export default LoginPage