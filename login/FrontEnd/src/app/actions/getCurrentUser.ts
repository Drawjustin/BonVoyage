"use server"
import axios from 'axios';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';

export default async function getCurrentUser() {
    try {
        const session = await getServerSession(authOptions);

        if (!session?.user?.name) {
            return null;
        }

        const id = session.user.name;

        const backendUrl = 'https://i10a207.p.ssafy.io/api';
        const currentUser = await axios.get(`${backendUrl}/artists/${id}`)
        
        if (!currentUser) {
            return null;
        }

        return currentUser;
    } catch (error) {
        // 오류가 발생하면 null 반환
        console.error("Error fetching user data:", error);
        return null;
    }
}