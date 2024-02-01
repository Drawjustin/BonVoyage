import axios from "axios";

export default async function getCurrentUser() {
    try {
        const backendUrl = 'http://43.200.244.3:8001';
        const response = await axios.get(`${backendUrl}/artists`);

        if (!response.data) {
            // artists 엔드포인트에서 데이터가 없을 경우 members 엔드포인트 호출
            const membersResponse = await axios.get(`${backendUrl}/members`);
            return membersResponse.data?.user || null;
        }

        const sessionData = response.data;

        if (!sessionData?.data.user) {
            return null;
        }

        return sessionData.user;
    } catch (error) {
        // 오류가 발생하면 null 반환
        console.error("Error fetching user data:", error);
        return null;
    }
}