export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.NEXT_PUBLIC_KAKAO_API_KEY}&redirect_uri=${process.env.NEXT_PUBLIC_KAKAO_REDIRECT_URI}&response_type=code`;

export const LOGIN = '/'; // 로그인 페이지
export const MAIN = '/main'; // 메인 페이지
export const EDIT = '/edit'; // 작성 페이지
