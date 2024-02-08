import React from 'react'
import Form from '../../../components/Signup/ArtistInputSignup/ArtistInputSignup'
import Navbar from '../../../components/Navbar/Navbar';
import getCurrentUser from '@/app/actions/getCurrentUser';

const SignupArtistPage = async () => {
  const currentUser = await getCurrentUser();
  return (
      <div>
      <Navbar/>
        <Form />
      </div>
  )
}

export default SignupArtistPage
