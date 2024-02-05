import { User } from "@prisma/client";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useMemo } from "react";
import { toast } from "react-toastify";

interface useFavoriteForReview {
    reviewId: string;
    currentUser?: User | null;
}

const useFavoriteForReview = ({reviewId, currentUser}: useFavoriteForReview) => {
    const router = useRouter();
    const hasFavorite = useMemo(() => {
        const list = currentUser?.favoriteIds || [];
        return list.includes(reviewId);
    }, [currentUser, reviewId])

    const toggleFavorite = async (e: React.MouseEvent<HTMLDivElement>) => {
        e.stopPropagation();

        if(!currentUser) {
            toast.warning('먼저 로그인을 해주세요');
            return;
        }

        try {
            let request;

            if(hasFavorite) {
                request = () => axios.delete(`/api/favorites/${reviewId}`);
            } else {
                request = () => axios.post(`/api/favorites/${reviewId}`);
            }

            await request();
            router.refresh();
            toast.success('성공했습니다!');

        } catch (err) {
            toast.error('실패했습니다.');
        }
    }

    return {
        hasFavorite,
        toggleFavorite
    }
}

export default useFavoriteForReview;