import { GetPostResponseDto } from '@/apis/post';
import { cookies } from 'next/headers';
import { ApiResponse } from '@/apis/types';
import { PostItem } from '@/components/blocks/PostItem';

type Props = {
  params: {
    id: string;
  };
};

const fetchPostData = async (id: number): Promise<GetPostResponseDto> => {
  const cookieStore = cookies();
  const accessToken = cookieStore.get('accessToken')?.value;

  const response = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/post/${id}`, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  const responseData: ApiResponse = await response.json();

  return responseData.data;
};

export default async function PostDetail({ params: { id } }: Props) {
  const data = await fetchPostData(Number(id));

  return (
    <div>
      <PostItem data={data} />
    </div>
  );
}
