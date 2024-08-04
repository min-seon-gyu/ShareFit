'use client';

import { GetPostsResponseDto } from '@/apis/post';
import { ApiResponse } from '@/apis/types';
import { useInfiniteScroll } from '@/hooks/interaction/useInfiniteScroll';
import { useRef, useState } from 'react';
import * as styles from './MemberImageLoader.css';
import Link from 'next/link';
import { POST } from '@/constants/routes';
import Image from 'next/image';

interface Props {
  totalPages: number;
  uuid: string;
}

type Post = GetPostsResponseDto['posts'][0];

export function MemberImageLoader({ totalPages, uuid }: Props) {
  const observerRef = useRef<HTMLDivElement>(null);

  const [page, setPage] = useState(0);
  const [posts, setPosts] = useState<Post[]>([]);

  const isLoading = useRef(false);

  const getNextPagePosts = async () => {
    if (isLoading.current || page === totalPages) return;

    isLoading.current = true;

    const nextPage = page + 1;

    const response = await fetch(
      `${process.env.NEXT_PUBLIC_BASE_URL}/posts?uuid=${uuid}&page=${nextPage}`,
    );
    const responseData: ApiResponse<GetPostsResponseDto> = await response.json();

    console.log(responseData.data);

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
      <div className={styles.content}>
        {posts.map((post) => (
          <Link href={`${POST}/${post.id}`} key={post.id}>
            <div className={styles.imageWrap}>
              <Image src={post.imagePath} alt={`post-img-${post.id}`} layout="fill" />
            </div>
          </Link>
        ))}
      </div>
      <div ref={observerRef} />
    </>
  );
}
