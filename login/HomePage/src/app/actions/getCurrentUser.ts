import { useSession } from "next-auth/react";

export default async function getCurrentUser() {
    try {
            const {data:session} = useSession();

            if (!session?.user) {
                return null;
            }
            return session.user;
    } catch (error) {
        return null;
    }
}