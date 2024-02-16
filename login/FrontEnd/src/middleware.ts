import { NextRequest, NextResponse } from 'next/server';
import getCurrentUser from './app/actions/getCurrentUser';

export const middleware = (req:NextRequest) => {
    const session = getCurrentUser();
    const LoggedOn = session?.id ? true : false;
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

    // return NextResponse.next();
}