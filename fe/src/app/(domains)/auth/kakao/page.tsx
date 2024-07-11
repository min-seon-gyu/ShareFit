'use client';

import { getKakaoInfoApi, getKakaoTokenApi, loginApi, refreshTokenApi } from '@/apis/auth';
import { useRouter, useSearchParams } from 'next/navigation';
import { useEffect, useRef } from 'react';
import { setCookie } from 'cookies-next';

export default function Kakao() {
  const router = useRouter();
  const params = useSearchParams();

  const code = params.get('code');

  const isRequest = useRef(false);

  useEffect(() => {
    if (!code || isRequest.current) return;

    isRequest.current = true;

    const handleLogin = async () => {
      const result = await login(code);

      if (result) {
        router.push('/main');
      }
    };

    handleLogin();
  }, [code, router]);

  return null;
}

const login = async (code: string): Promise<boolean> => {
  try {
    const { data: tokenData } = await getKakaoTokenApi({ code });
    const { access_token } = tokenData;

    const { data: kakaoData } = await getKakaoInfoApi({ accessToken: access_token });

    const { id: uuid } = kakaoData;
    const { nickname } = kakaoData.kakao_account.profile;

    await loginApi({ uuid, nickname });

    /**
     *  @todo access_token 저장하기
     */

    return true;
  } catch (err) {
    console.log(err);
  }

  return false;
};
