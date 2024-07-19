'use client';

import { getKakaoInfoApi, getKakaoTokenApi, loginApi, refreshTokenApi } from '@/apis/auth';
import { useRouter, useSearchParams } from 'next/navigation';
import { useEffect, useRef } from 'react';
import { setCookie } from 'cookies-next';

export default function Kakao() {
  const router = useRouter();
  const params = useSearchParams();

  const code = params.get('code');

  useEffect(() => {
    if (!code) return;

    console.log(1111);

    const handleLogin = async () => {
      await login(code);

      router.replace('/main');
    };

    handleLogin();
  }, [code, router]);

  return null;
}

const login = async (code: string): Promise<void> => {
  try {
    const { data: tokenData } = await getKakaoTokenApi({ code });
    const { access_token } = tokenData;

    const { data: kakaoData } = await getKakaoInfoApi({ accessToken: access_token });

    const { id: uuid } = kakaoData;
    const { nickname } = kakaoData.kakao_account.profile;

    const res = await loginApi({ uuid, nickname });

    const { accessToken } = res.data.data;
    setCookie('accessToken', accessToken);
  } catch (err) {
    console.log(err);
  }
};
