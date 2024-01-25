import getCurrentUser from "@/app/actions/getCurrentUser";
import { NextResponse } from "next/server";
import prisma from '@/helpers/prismadb'

export async function POST(request: Request) {
    const currentUser = await getCurrentUser()
    if(!currentUser) {
        return NextResponse.error();
    }

    const body = await request.json()

    const {
        title,
        description,
        imageSrc,
    } = body;

    Object.keys(body).forEach((value) => {
        if(!body[value]) {
            return NextResponse.error();
        }
    })
    const review = await prisma.review.create({
        data: {
            title,
            description,
            imageSrc,
            userId: currentUser.id
        }
    })

    return NextResponse.json(review);

}