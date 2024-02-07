import { getToken } from 'next-auth/jwt';
import { NextRequest, NextResponse } from 'next/server';

export {default} from 'next-auth/middleware';

export async function middleware(req:NextRequest) {
    const session = await getToken({ req, secret: process.env.JWT_SECRET});
    const pathname = req.nextUrl.pathname;

    if (pathname.startsWith('/user') && !session) {
        return NextResponse.redirect(new URL("/LoginPage", req.url));
    }

    if (pathname.startsWith('/MyPage') && !session) {
        return NextResponse.redirect(new URL("/LoginPage", req.url));
    }

    if ((pathname.startsWith('/AuctionUploadPage') ||
        pathname.startsWith('/ProductUploadPage') ||
        pathname.startsWith('/CartPage') ||
        pathname.startsWith('/AuctionLivePage')) && !session) {
        return NextResponse.redirect(new URL("/LoginPage", req.url));
    }

    if ((pathname.startsWith('/LoginPage') || (pathname.startsWith('/SignupPage'))) && session) {
        return NextResponse.redirect(new URL("/", req.url));
    }

    // // 어드미 유저만 접근 가능
    // if (pathname.startsWith('/admin') && (session?.role !== 'admin')) {
    //     return NextResponse.redirect(new URL('/', req.url));
    // }

    // if (pathname.startsWith('/auth') && session) {
    //     return NextResponse.redirect(new URL('/', req.url));
    // }

    return NextResponse.next();
}