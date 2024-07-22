'use client';

import { getKakaoInfoApi, getKakaoTokenApi, loginApi } from '@/apis/auth';
import { useRouter, useSearchParams } from 'next/navigation';
import { useEffect, useRef } from 'react';
import { setCookie } from 'cookies-next';
import { LOGIN, POST } from '@/constants/routes';

export default function Kakao() {
  const router = useRouter();
  const params = useSearchParams();

  const code = params.get('code');

  const isRequest = useRef(false);

  useEffect(() => {
    if (!code || isRequest.current) return;

    isRequest.current = true;

    const handleLogin = async () => {
      try {
        await login(code);
        router.push(POST);
      } catch {
        router.push(LOGIN);
      }
    };

    handleLogin();
  }, [code, router]);

  return null;
}

const login = async (code: string): Promise<void> => {
  const { data: tokenData } = await getKakaoTokenApi({ code });
  const { access_token } = tokenData;

  const { data: kakaoData } = await getKakaoInfoApi({ accessToken: access_token });

  const { id: uuid } = kakaoData;
  const { nickname, profile_image_url: profilePath } = kakaoData.kakao_account.profile;

  const res = await loginApi({ uuid, nickname, profilePath });

  const { accessToken } = res.data.data;

  setCookie('accessToken', accessToken, {
    maxAge: 60 * 5,
  });
};
