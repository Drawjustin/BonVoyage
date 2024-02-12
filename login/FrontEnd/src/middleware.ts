import { getToken } from 'next-auth/jwt';
import { NextRequest, NextResponse } from 'next/server';

export {default} from 'next-auth/middleware';

export async function middleware(req:NextRequest) {
    const session = await getToken({ req, secret: process.env.JWT_SECRET});
    const LoggedOn = session?.artistId ? session?.artistId : session?.memberId;
    const pathname = req.nextUrl.pathname;

    // if (pathname.startsWith('/MyPage') && !LoggedOn) {
    //     return NextResponse.redirect(new URL("/LoginPage", req.url));
    // }

    // if ((pathname.startsWith('/AuctionUploadPage') ||
    //     pathname.startsWith('/ProductUploadPage') ||
    //     pathname.startsWith('/CartPage') ||
    //     pathname.startsWith('/AuctionLivePage')) && !LoggedOn) {
    //     return NextResponse.redirect(new URL("/LoginPage", req.url));
    // }

    // if ((pathname.startsWith('/LoginPage') || (pathname.startsWith('/SignupPage'))) && LoggedOn) {
    //     return NextResponse.redirect(new URL("/", req.url));
    // }

    return NextResponse.next();
}