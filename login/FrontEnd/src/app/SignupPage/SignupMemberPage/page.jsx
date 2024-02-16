import React from 'react'
import Form from '../../../components/Signup/MemberInputSignup/MemberInputSignup'
import getCurrentUser from '@/app/actions/getCurrentUser';

const SignupMemberPage = async () => {
  const currentUser = await getCurrentUser();
  return (
      <div>
        <Form />
      </div>
  )
}

export default SignupMemberPage