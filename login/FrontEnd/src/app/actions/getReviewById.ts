import prisma from '@/helpers/prismadb'

interface Params {
    reviewId?: string
}

export default async function getReviewById (params: Params) {
    try {
        const {reviewId} = params;

        const review = await prisma.review.findUnique({
            where : {
                id: reviewId
            },
            include: {
                user: true
            }
        });

        if (!review) {
            return null;
        }

        return review;
    } catch (error: any) {
        throw new Error(error);
    }
}