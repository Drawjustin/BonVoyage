
import NextAuth, { NextAuthOptions } from "next-auth"
import CredentialsProvider from "next-auth/providers/credentials"
import {NextApiRequest} from "next";
import bcrypt from 'bcryptjs';
import axios, { AxiosResponse } from "axios";

type User = {
  name: string;
  id: string;
};

export const authOptions : NextAuthOptions = {
  providers: [
    CredentialsProvider({
      id: "credentials",
      name: "Credentials",
      credentials: {
        username: { label: "username", type: "text", placeholder: "ID 입력" },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials, req) {
        try {
          if (!credentials?.username || !credentials?.password) {
            console.log('error');
            return null;
          }
          const backendUrl = "http://43.200.244.3:8001";

          // Artist
          const userResponse: AxiosResponse<any> = await axios.post(`${backendUrl}/artists/login`, credentials, {
                     headers: {
                       'Content-Type': 'application/json;charset=UTF-8'
                     }
                   });
          const userConfirm = userResponse.data;

          if (userConfirm !== 'Login Successful') {
            // Member
            const memberResponse: AxiosResponse<any> = await axios.post(`${backendUrl}/members/login`, credentials, {
                       headers: {
                         'Content-Type': 'application/json;charset=UTF-8'
                       }
                     });
            const memberConfirm = memberResponse.data;

            if (memberConfirm !== 'Login Successful') {
              console.log('error');
              return null;
            }

            const memberData:User = await axios.get(`${backendUrl}/members?membersId=${credentials.username}`)

              return memberData;
            
          }
          const userData:User = await axios.get(`${backendUrl}/artists?artistsId=${credentials.username}`)
          return userData;
        } catch (error) {
          console.error("Authorization error:", error);
          return null;
        }
      },
    }),
  ],
  session: {
    strategy:'jwt'
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
      if (user) {
        token.id = user.id;
        token.name = user.name;
      }
      return token;
    },
    async session({session, token}) {
      session.user = token;
      return session;
    },
  },
};

export default NextAuth(authOptions);

// import NextAuth, { NextAuthOptions } from "next-auth"
// import GoogleProvider from "next-auth/providers/google"
// import CredentialsProvider from "next-auth/providers/credentials"
// import bcrypt from 'bcryptjs';

// export const authOptions : NextAuthOptions = {
//   providers: [
//     GoogleProvider({
//       clientId: process.env.GOOGLE_CLIENT_ID!,
//       clientSecret: process.env.GOOGLE_CLIENT_SECRET!,
//     }),
//     CredentialsProvider({
//         // The name to display on the sign in form (e.g. "Sign in with...")
//         name: "Credentials",
//         // `credentials` is used to generate a form on the sign in page.
//         // You can specify which fields should be submitted, by adding keys to the `credentials` object.
//         // e.g. domain, username, password, 2FA token, etc.
//         // You can pass any HTML attribute to the <input> tag through the object.
//         credentials: {
//           email: { label: "Email", type: "text" },
//           password: { label: "Password", type: "password" }
//         },
//         async authorize(credentials, req) {
//           if (!credentials?.email || !credentials?.password) {
//             throw new Error('Invalid credentials');
//           }

//           const user = await prisma.user.findUnique({
//             where: {
//               email: credentials.email
//             }
//           })
          
//           if(!user || !user?.hashedPassword) {
//             throw new Error('Invalid credentials');
//           }

//           const isCorrectPassword = await bcrypt.compare(
//             credentials.password,
//             user.hashedPassword
//           )
          
//             if (!isCorrectPassword) {
//               throw new Error('Invalid credentials');
//             }

//           return user;

//           }
//       })
//   ],
//   session: {
//     strategy:'jwt'
//   },
//   jwt: {
//     secret: process.env.JWT_SECRET,
//     maxAge: 30 * 24 * 60 * 60 // 30days
//   },
//   pages: {
//     signIn: '/auth/login'
//   },
//   callbacks: {
//     async jwt({token, user}) {
//       return {...token, ...user}
//     },
//     async session({session, token}) {
//       session.user = token;
//       return session;
//     } 
//   }
// }

// export default NextAuth(authOptions);