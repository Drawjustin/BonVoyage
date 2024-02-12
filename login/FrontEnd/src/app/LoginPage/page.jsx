import React from 'react'
import Navbar from '@/components/Navbar/Navbar';
import InputLogin from '@/components/InputLogin/InputLogin';
import getCurrentUser from '@/app/actions/getCurrentUser';

const LoginPage = () => {

  const currentUser = getCurrentUser();

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