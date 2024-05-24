import React from 'react'
import Form from '../../../components/Signup/ArtistInputSignup/ArtistInputSignup'
import getCurrentUser from '@/app/actions/getCurrentUser';

const SignupArtistPage = async () => {
  const currentUser = await getCurrentUser();
  return (
      <div>
        <Form />
      </div>
  )
}

export default SignupArtistPage
