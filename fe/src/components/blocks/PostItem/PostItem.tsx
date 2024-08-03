'use client';

import * as styles from './PostItem.css';
import { ProfileIcon } from '@/components/atoms/ProfileIcon';
import { Typography } from '@/components/atoms/Typography';
import Image from 'next/image';
import Icon from '@/components/atoms/icon/Icon';
import { black, gray, red } from '@/styles/Color';
import Link from 'next/link';
import { MEMBER, POST } from '@/constants/routes';
import { useAddLike, useCacncelLike } from '@/apis/like';
import { createContext, Dispatch, SetStateAction, useState } from 'react';
import { GetPostResponseDto, GetPostsResponseDto } from '@/apis/post';
import Comments from './Comments';

// 리스트 아이템, 상세 아이템
type Post = GetPostsResponseDto['posts'][0] | GetPostResponseDto;

type Props = {
  data: Post;
};

// type guard
const isPostDetail = (value: Post): value is GetPostResponseDto => {
  return 'comments' in value;
};

export const PostItemContext = createContext<{
  post: GetPostResponseDto;
  setPost: Dispatch<SetStateAction<Post>>;
} | null>(null);

export function PostItem({ data }: Props) {
  const [post, setPost] = useState<Post>(data);

  const { id, profilePath, memberId, nickname, likeCount, isLike, imagePath, content } = post;

  const POST_DETAIL = `${POST}/${id}`;
  const MEMBER_DETAIL = `${MEMBER}/${memberId}`;

  const { addLikeMutate } = useAddLike();
  const { cancelLikeMutate } = useCacncelLike();

  /** 좋아요 핸들링 */
  const handleLike = (id: number) => {
    if (!isLike) {
      addLikeMutate(id, {
        onSuccess: () => {
          setPost((prev) => ({ ...prev, isLike: true, likeCount: prev.likeCount + 1 }));
        },
      });
      return;
    }

    cancelLikeMutate(id, {
      onSuccess: () => {
        setPost((prev) => ({ ...prev, isLike: false, likeCount: prev.likeCount - 1 }));
      },
    });
  };

  return (
    <div>
      <div className={styles.header}>
        <ProfileIcon profilePath={profilePath ?? undefined} id={memberId} size="medium" />
        <Link href={MEMBER_DETAIL}>
          <Typography variant="b2">{nickname}</Typography>
        </Link>
      </div>
      <div className={styles.imageWrap}>
        <Image src={imagePath} layout="fill" alt={`post-img-${id}`} />
      </div>
      <div className={styles.content}>
        <div className={styles.iconWrap}>
          <HeartButton isLike={isLike} onClick={() => handleLike(id)} />
          <Link href={POST_DETAIL}>
            <Icon className={styles.icon} name="chat" fill={black} />
          </Link>
        </div>
        <Typography variant="sh4">좋아요 {likeCount}개</Typography>

        {/* only List */}
        {!isPostDetail(post) && (
          <Link href={POST_DETAIL}>
            <Typography color={gray.gray6}>댓글 {post.commentCount}개 모두 보기</Typography>
          </Link>
        )}
        {/* only List end */}

        <Typography variant="b3">
          <b>{nickname}</b> {content}
        </Typography>

        {/* only Detail */}
        {isPostDetail(post) && (
          <PostItemContext.Provider value={{ post, setPost }}>
            <Comments />
          </PostItemContext.Provider>
        )}
        {/* only Detail end */}
      </div>
    </div>
  );
}

function HeartButton({ isLike, onClick }: { isLike: boolean; onClick: () => void }) {
  return (
    <Icon
      className={styles.icon}
      name="heart"
      onClick={onClick}
      stroke={isLike ? red.red3 : black}
      fill={isLike ? red.red3 : 'transparent'}
    />
  );
}
