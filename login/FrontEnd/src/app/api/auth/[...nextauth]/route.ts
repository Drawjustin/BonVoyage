import NextAuth, { NextAuthOptions } from "next-auth"
import CredentialsProvider from "next-auth/providers/credentials"
import axios, { AxiosResponse } from "axios";
import { authOptions } from "@/utils/authOptions";

const handler = NextAuth(authOptions);

export {handler as GET, handler as POST};