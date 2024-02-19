'use client'
import React from 'react';
import { GoogleLogin } from '@react-oauth/google';
import {GoogleOAuthProvider} from "@react-oauth/google";
import styles from './button.module.scss';

const GoogleLoginButton = () => {
  const clientId = 'clientID'
  return (
      <div className={styles.b2}>
          <GoogleOAuthProvider clientId={clientId}>
              <GoogleLogin
                  onSuccess={(res) => {
                      console.log(res);
                  }}
                  onFailure={(err) => {
                      console.log(err);
                  }}
              />
          </GoogleOAuthProvider>
      </div>
  );
};

export default GoogleLoginButton;
