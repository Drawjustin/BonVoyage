import React from 'react'
import InputLogin from '@/components/InputLogin/InputLogin';
import getCurrentUser from '@/app/actions/getCurrentUser';

const LoginPage = () => {

  const currentUser = getCurrentUser();

    return (
      
        
    <div>
      <div style={{ marginTop: '10vh' }}>
        <InputLogin/>
      </div>
    </div>





    )
      }

export default LoginPage