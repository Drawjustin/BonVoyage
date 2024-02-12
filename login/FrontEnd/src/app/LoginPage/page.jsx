'use server'
import React from 'react'
import Navbar from '@/components/Navbar/Navbar';
import InputLogin from '@/components/InputLogin/InputLogin';
import { getProviders } from 'next-auth/react';

async function LoginPage() {

    const provider = await getProviders();
    console.log(provider);
  
    return (
      
        
    <div>

    <div>
      <Navbar />
      <div style={{ marginTop: '10vh' }}>
        <InputLogin/>
      </div>
    </div>
    </div>




    )
      }

export default LoginPage