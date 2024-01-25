import { PRODUCTS_PER_PAGE } from "@/constants";

export interface ReviewsParams {
    page?: number;
    skip?: number;
}

export default async function getReviews(params: ReviewsParams) {
    try {
        const {skip} = params;
        let query: any = {};

        const totalItems = await prisma?.review.count({ where: query});

        const reviews = await prisma?.review.findMany({
            where: query,
            orderBy: {
                createdAt: 'desc'
            },
            skip: skip ? Number(skip) :0 ,
            take: PRODUCTS_PER_PAGE
        })

        return {
            data: reviews,
            totalItems
        }
    } catch (error: any) {
        throw new Error(error);
    }
}