import React from 'react'
import Form from '../../../components/Signup/MemberInputSignup/MemberInputSignup'
import Navbar from '../../../components/Navbar/Navbar';
import getCurrentUser from '@/app/actions/getCurrentUser';

const SignupMemberPage = async () => {
  const currentUser = await getCurrentUser();
  return (
      <div>
      <Navbar/>
        <Form />
      </div>
  )
}

export default SignupMemberPage