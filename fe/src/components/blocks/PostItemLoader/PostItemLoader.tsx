'use client';

import { GetPostsResponseDto } from '@/apis/post';
import { useInfiniteScroll } from '@/hooks/interaction/useInfiniteScroll';
import { useRef, useState } from 'react';
import { PostItem } from '../PostItem';
import { ApiResponse } from '@/apis/types';

interface Props {
  totalPages: number;
}

type Post = GetPostsResponseDto['posts'][0];

export function PostItemLoader({ totalPages }: Props) {
  const observerRef = useRef<HTMLDivElement>(null);

  const [page, setPage] = useState(0);
  const [posts, setPosts] = useState<Post[]>([]);

  const isLoading = useRef(false);

  const getNextPagePosts = async () => {
    if (isLoading.current || page === totalPages) return;

    isLoading.current = true;

    const nextPage = page + 1;

    const response = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/posts?page=${nextPage}`);
    const responseData: ApiResponse<GetPostsResponseDto> = await response.json();

    const newPosts = responseData.data.posts;

    setPage(nextPage);
    setPosts((prev) => [...prev, ...newPosts]);

    isLoading.current = false;
  };

  useInfiniteScroll({
    ref: observerRef,
    callback: getNextPagePosts,
  });

  return (
    <>
      {posts?.map((post) => <PostItem key={`newPost-${post.id}`} data={post} />)}
      <div ref={observerRef} />
    </>
  );
}
