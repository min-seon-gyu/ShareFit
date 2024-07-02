import { Typography } from '@/components/Typography';
import { pageStyle } from './login.css';
import { white } from '@/styles/Color';
import { KakaoLoginButtonStyle } from '../layout.css';
import KakaoImg from '@/assets/images/kakao.png';
import Image from 'next/image';

export default function Login() {
  return (
    <div className={pageStyle}>
      <Typography as="h1" color={white} variant="h2">
        ShareFit
      </Typography>
      <Typography as="h3" color={white} variant="sh4">
        오늘의 스타일을 세상과 공유하다
      </Typography>

      <div style={{ marginTop: 24 }}>
        <KakaoLoginButton />
      </div>
    </div>
  );
}

function KakaoLoginButton() {
  return (
    <button className={KakaoLoginButtonStyle}>
      <Image width={20} height={20} src={KakaoImg} alt="kakao" />

      <Typography variant="sh4" style={{ flex: 1 }}>
        카카오 로그인
      </Typography>
    </button>
  );
}
