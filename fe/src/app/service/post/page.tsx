import { GetPostsResponseDto } from '@/apis/post';
import { ApiResponse } from '@/apis/types';
import { PostItem } from '@/components/blocks/PostItem';
import { PostItemLoader } from '@/components/blocks/PostItemLoader';
import { cookies } from 'next/headers';

const fetchPostsData = async (): Promise<GetPostsResponseDto> => {
  const cookieStore = cookies();
  const accessToken = cookieStore.get('accessToken')?.value;

  const response = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/posts`, {
    method: 'GET',
    cache: 'no-store',
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  const responseData: ApiResponse = await response.json();

  return responseData.data;
};

export default async function PostPage() {
  const data = await fetchPostsData();
  const { posts, totalPages } = data ?? {};

  return (
    <div>
      {posts?.map((post) => <PostItem key={post.id} data={post} />)}

      {/* infinite Scroll Loader */}
      {totalPages > 1 && <PostItemLoader totalPages={totalPages} />}
    </div>
  );
}
