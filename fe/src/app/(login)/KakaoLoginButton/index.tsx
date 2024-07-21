'use client';

import { KakaoLoginButtonStyle } from './KakaoLoginButton.css';
import KakaoLoginButtonImg from '@/assets/images/kakao-login-button.png';
import Image from 'next/image';
import { KAKAO_AUTH_URL } from '@/constants/routes';

export function KakaoLoginButton() {
  return (
    <a href={KAKAO_AUTH_URL}>
      <button className={KakaoLoginButtonStyle}>
        <Image layout="fill" src={KakaoLoginButtonImg} alt="kakao" />
      </button>
    </a>
  );
}
