import { GetPopularPostsResponseDto, GetPostsResponseDto } from '@/apis/post';
import { ApiResponse } from '@/apis/types';
import { PostItem } from '@/components/blocks/PostItem';
import { PostItemLoader } from '@/components/blocks/PostItemLoader';
import { cookies } from 'next/headers';
import * as styles from './page.css';
import { Typography } from '@/components/atoms/Typography';
import { PopularItem } from '@/components/blocks/PopularItem';

const fetchPostsData = async (): Promise<GetPostsResponseDto> => {
  const cookieStore = cookies();
  const accessToken = cookieStore.get('accessToken')?.value;

  const response = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/posts`, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  const responseData: ApiResponse = await response.json();

  return responseData.data;
};

const fetchPopularPostsData = async (): Promise<GetPopularPostsResponseDto> => {
  const cookieStore = cookies();
  const accessToken = cookieStore.get('accessToken')?.value;

  const response = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/posts/popular`, {
    method: 'GET',
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

  const popularData = await fetchPopularPostsData();

  return (
    <div>
      {!!popularData.posts.length && (
        <div className={styles.popularSection}>
          <Typography variant="sh3">오늘의 인기 게시물 🏆</Typography>
          <div className={styles.popularItemWrap}>
            {popularData.posts.map((popularPost) => (
              <PopularItem key={popularPost.id} data={popularPost} />
            ))}
          </div>
        </div>
      )}

      {posts?.map((post) => <PostItem key={post.id} data={post} />)}

      {/* infinite Scroll Loader */}
      {totalPages > 1 && <PostItemLoader totalPages={totalPages} />}
    </div>
  );
}
