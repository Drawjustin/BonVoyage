import { NextAuthOptions } from "next-auth"
import CredentialsProvider from "next-auth/providers/credentials"
import axios, { AxiosResponse } from "axios";

interface CustomUserType {
    artistId: string;
    memberId: string;
  }
  
  declare module 'next-auth' {
    interface Session {
      user: CustomUserType;
    }
  }
export const authOptions:NextAuthOptions = {
  providers: [
    CredentialsProvider({
      name: "Credentials",
      credentials: {
        username: { label: "Username", type: "text" },
        password: { label: "Password", type: "password" }
      },
      async authorize(credentials, req) {

          try {
            const backendUrl = "https://i10a207.p.ssafy.io/api";
            // Artist
            const artistBody = {
                id: credentials?.username,
                pw: credentials?.password
            }
  
            const userResponse: AxiosResponse<any> = await axios.post(`${backendUrl}/artists/login`, artistBody, {
              headers: {
                'Content-Type': 'application/json;charset=UTF-8'
              }
            });

            if (userResponse.data === "바보 멍텅구리 로그인 실패했잔요") {
              // Member

              const memberBody = {
                id: credentials?.username,
                pw: credentials?.password
            }

              const memberResponse: AxiosResponse<any> = await axios.post(`${backendUrl}/members/login`, memberBody, {
                headers: {
                  'Content-Type': 'application/json;charset=UTF-8'
                }
              });
  
              if (memberResponse.data === "바보 멍텅구리 로그인 실패했잔요") {
                console.log('error');
                return null;
              }

              return memberResponse.data;
            }

            return userResponse.data;
        
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
  secret: process.env.NEXTAUTH_SECRET,
  jwt: {
    secret: process.env.JWT_SECRET,
    maxAge: 30 * 24 * 60 * 60 // 30days
  },
  pages: {
    signIn: '/LoginPage',
  },
  callbacks: {
    async jwt({ token, user }) {
        //console.log(token);
        // console.log('체크', token, user);
        // Use optional chaining to safely access properties

        //console.log( {...token, ...user, artistId, memberId })
        if (!token.name){
          return { ...token, ...user};
        }
        return token;
      },
    async session({ session, token }) {        
      const artistId = token?.artistId as string;
      const memberId = token?.memberId as string;
    
      session.user = { artistId, memberId };
      return session;
    },
  },
};