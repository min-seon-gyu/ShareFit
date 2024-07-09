'use client';

import { KAKAO_API_KEY, KAKAO_REDIRECT_URI } from '@/constants/routes';
import { useSearchParams } from 'next/navigation';
import { useEffect, useRef } from 'react';

export default function Kakao() {
  const params = useSearchParams();

  const code = params.get('code');

  const isRequest = useRef(false);

  useEffect(() => {
    if (!code || isRequest.current) return;

    isRequest.current = true;

    const body = {
      grant_type: 'authorization_code',
      client_id: KAKAO_API_KEY,
      redirect_uri: KAKAO_REDIRECT_URI,
      code,
    };

    const getToken = async () => {
      const response = await fetch('https://kauth.kakao.com/oauth/token', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
        },
        body: new URLSearchParams(body).toString(),
      }).then((res) => res.json());

      const { access_token } = response;

      await getInfo(access_token);
    };

    const getInfo = async (accessToken: string) => {
      const response = await fetch('https://kapi.kakao.com/v2/user/me', {
        method: 'POST',
        headers: {
          Authorization: `Bearer ${accessToken}`,
          'Content-type': 'application/x-www-form-urlencoded;charset=utf-8',
        },
      }).then((res) => res.json());

      const { id } = response;
      const { nickname } = response.kakao_account.profile;
    };

    getToken();
  }, [code]);

  return null;
}
