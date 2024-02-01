import axios from "axios";

export default async function getCurrentUser() {
    try {

            const backendUrl = 'http://43.200.244.3:8001'
            const response = await axios.get(`${backendUrl}/artists`);
            
            if (!response.data) {
                await axios.get(`${backendUrl}/members`);
            }
            const sessionData = response.data;

            if (!sessionData?.data.user) {
                return null;
            }
            return sessionData.user;
    } catch (error) {
        return null;
    }
}