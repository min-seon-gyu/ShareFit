'use client';

import { Typography } from '@/components/atoms/Typography';
import { KakaoLoginButtonStyle } from './KakaoLoginButton.css';
import KakaoImg from '@/assets/images/kakao.png';
import Image from 'next/image';

export function KakaoLoginButton() {
  return (
    <button className={KakaoLoginButtonStyle}>
      <Image width={20} height={20} src={KakaoImg} alt="kakao" />

      <Typography variant="sh4" style={{ flex: 1 }}>
        카카오 로그인
      </Typography>
    </button>
  );
}
