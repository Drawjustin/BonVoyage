import { NextAuthOptions } from "next-auth"
import CredentialsProvider from "next-auth/providers/credentials"
import axios, { AxiosResponse } from "axios";
  

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

              return {id : memberResponse.data};
            }

            return {id : userResponse.data};
        
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
    maxAge: 30 * 24 * 60 * 60, // 30days
  },
  pages: {
    signIn: '/LoginPage',
  },
  callbacks: {
    async jwt({ token, user }) {
        //console.log(token);
        // console.log('체크', token, user);
        // Use optional chaining to safely access properties
        //console.log( {...token, ...user, artistId, memberId });
        if (!token.sub){
          return { ...token, ...user};
        }
        return token;
      },
    async session({ session, token }) { 
    
      const artistId = token?.sub as string;
      const memberId = token?.sub as string;
    
      session.user = {...session.user, artistId, memberId };

      return session;
    },
  },
};