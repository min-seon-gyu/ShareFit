import { NextRequest, NextResponse } from 'next/server';
import { KAKAO_OAUTH_CALLBACK, LOGIN, POST } from './constants/routes';

/**
 *  @middleware
 *  로그인 여부에 따른 페이지 접근 제어
 */
export function middleware(request: NextRequest) {
  const accessToken = request.cookies.get('accessToken')?.value;
  const pathUrl = request.nextUrl.pathname;

  // oauth callback은 미들웨어 제외
  if (pathUrl === KAKAO_OAUTH_CALLBACK) {
    return;
  }

  // 토큰 O, 로그인 페이지 접근시 메인 페이지로 리디렉트
  if (accessToken && pathUrl === LOGIN) {
    return NextResponse.redirect(new URL(POST, request.url));
  }

  // 토큰 X, 페이지 접근시 로그인 페이지로 리디렉트
  if (!accessToken && pathUrl !== LOGIN) {
    return NextResponse.redirect(new URL('/', request.url));
  }
}

export const config = {
  matcher: [
    {
      source: '/((?!api|_next/static|_next/image|favicon.ico|server).*)',
      missing: [
        { type: 'header', key: 'next-router-prefetch' },
        { type: 'header', key: 'purpose', value: 'prefetch' },
      ],
    },
  ],
};
