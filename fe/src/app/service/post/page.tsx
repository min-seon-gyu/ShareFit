import { GetPostsResponseDto } from '@/apis/post';
import { ApiResponse } from '@/apis/types';
import { cookies } from 'next/headers';

const fetchPostsData = async (): Promise<GetPostsResponseDto> => {
  const cookieStore = cookies();
  const accessToken = cookieStore.get('accessToken')?.value;

  const response = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/post`, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  const responseData: ApiResponse = await response.json();

  return responseData.data;
};

export default async function Post() {
  const data = await fetchPostsData();

  return <div>post list page</div>;
}
