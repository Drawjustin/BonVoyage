"use server"
import axios from 'axios';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';

export default async function getCurrentUser() {
    try {
        const session = await getServerSession(authOptions);

        const session = await getSession();

            if (!session) {
                return null;
            }

        const backendUrl = 'https://i10a207.p.ssafy.io/api';
        const response = await axios.get(`${backendUrl}/artists?artistsId=${session.user}`);
        
        console.log(response);

        if (!response.data) {
            // artists 엔드포인트에서 데이터가 없을 경우 members 엔드포인트 호출
            const membersResponse = await axios.get(`${backendUrl}/members?membersID=${session.user}`);
            return membersResponse.data || null;
        }

        const sessionData = response.data;

        if (!sessionData) {
            return null;
        }

        return response.data;
    } catch (error) {
        // 오류가 발생하면 null 반환
        console.error("Error fetching user data:", error);
        return null;
    }
}