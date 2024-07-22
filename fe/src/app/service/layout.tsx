import React from 'react';
import { pageStyle } from './layout.css';
import Header from '@/components/layouts/header';
import { cookies } from 'next/headers';
import { ApiResponse } from '@/apis/types';
import { GetMyInfoResponseDto } from '@/apis/member/member.types';

const fetchMyInfoData = async (): Promise<GetMyInfoResponseDto> => {
  const cookieStore = cookies();
  const accessToken = cookieStore.get('accessToken')?.value;

  const response = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/member`, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  const responseData: ApiResponse = await response.json();

  return responseData.data;
};

export default async function Layout({ children }: { children: React.ReactNode }) {
  const data = await fetchMyInfoData();

  return (
    <div className={pageStyle}>
      <Header data={data} />

      {children}
    </div>
  );
}
