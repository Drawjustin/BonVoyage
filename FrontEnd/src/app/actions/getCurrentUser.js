// import axios from 'axios';
// import { getServerSession } from 'next-auth';
// import { authOptions } from '@/utils/authOptions';

// interface CustomUserType {
//     artistId: string;
//     memberId: string;
//   }
  
//   declare module 'next-auth' {
//     interface Session {
//       user: CustomUserType;
//     }
//   }




const getCurrentUser = () => {
    try {
        const session = JSON.parse(sessionStorage.getItem('session'));
        if (!session) {
            return null;
        }
        return session;
    //     const backendUrl = `https://i10a207.p.ssafy.io/api`;

    //     if(session?.user.artistId) {
    //     const sessionId = session?.user.artistId
        
    //     const response = await axios.get(`${backendUrl}/artists/${sessionId}`);

    //     const sessionData = response.data;

    //     if (!sessionData) {
    //         return null;
    //     }
        
    //     return sessionData;
    // }  else {
    //     // artists 엔드포인트에서 데이터가 없을 경우 members 엔드포인트 호출
    //     const memberId = session?.user.memberId
    //     const membersResponse = await axios.get(`${backendUrl}/members/${memberId}`);

    //     if (!membersResponse) {
    //         return null;
    //     }
    //     return membersResponse.data;
    // }
    } catch (error) {
        // 오류가 발생하면 null 반환
        // console.error("Error fetching user data:", error);
        return null;
    }
}

export default getCurrentUser