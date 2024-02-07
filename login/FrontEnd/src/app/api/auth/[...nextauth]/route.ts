import NextAuth, { NextAuthOptions } from "next-auth"
import CredentialsProvider from "next-auth/providers/credentials"
import { NextApiRequest } from "next";
import axios, { AxiosResponse } from "axios";

export const authOptions:NextAuthOptions = {
  providers: [
    CredentialsProvider({
      name: "credentials",
      credentials: {
        id: { label: "Username", type: "text" },
        pw: { label: "Password", type: "password" }
      },
      async authorize(credentials) {
          try {
            const backendUrl = "https://i10a207.p.ssafy.io/api";
            // Artist
  
            const userResponse: AxiosResponse<any> = await axios.post(`${backendUrl}/artists/login`, credentials, {
              headers: {
                'Content-Type': 'application/json;charset=UTF-8'
              }
            });
            console.log('췍췍', userResponse.data);
            if (userResponse.data === "바보 멍텅구리 로그인 실패했잔요") {
              // Member
              const memberResponse: AxiosResponse<any> = await axios.post(`${backendUrl}/members/login`, credentials, {
                headers: {
                  'Content-Type': 'application/json;charset=UTF-8'
                }
              });
  
              if (memberResponse.data === "바보 멍텅구리 로그인 실패했잔요") {
                console.log('error');
                return null;
              }
  
              return {
                id: memberResponse.data.memberId,
              };
            }
  
            return {
              id: userResponse.data.artistId,
            };
          }
          catch (error) {
            console.log('에러에러', error);
            return null;
          }
      },
    }),
  ],
  session: {
    strategy: 'jwt'
  },
  jwt: {
    secret: process.env.JWT_SECRET,
    maxAge: 30 * 24 * 60 * 60 // 30days
  },
  pages: {
    signIn: '/LoginPage',
  },
  callbacks: {
    async jwt({token, user}) {
      return {...token, ...user}
    },
    async session({ session, token }) {
      session.user = token;
      return session;
    },
  },
};

const handler = NextAuth(authOptions);

export {handler as GET, handler as POST};