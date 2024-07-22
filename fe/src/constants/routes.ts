export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.NEXT_PUBLIC_KAKAO_API_KEY}&redirect_uri=${process.env.NEXT_PUBLIC_KAKAO_REDIRECT_URI}&response_type=code`;

export const LOGIN = '/'; // 로그인 페이지
export const POST = '/service/post'; // 게시글 페이지
export const EDIT = '/service/edit'; // 작성 페이지
export const MEMBER = '/service/member'; // 회원 페이지

export const KAKAO_OAUTH_CALLBACK = '/auth/kakao'; // 카카오 로그인 콜백
