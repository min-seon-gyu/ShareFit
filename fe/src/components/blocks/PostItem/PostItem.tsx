'use client';

import { Post } from '@/apis/post';
import * as styles from './PostItem.css';
import { ProfileIcon } from '@/components/atoms/ProfileIcon';
import { Typography } from '@/components/atoms/Typography';
import Image from 'next/image';
import Icon from '@/components/atoms/icon/Icon';
import { black, red } from '@/styles/Color';
import Link from 'next/link';
import { MEMBER, POST } from '@/constants/routes';
import { useAddLike, useCacncelLike } from '@/apis/like';
import { useState } from 'react';

type Props = {
  data: Post;
};

export function PostItem({ data }: Props) {
  const [post, setPost] = useState<Post>(data);

  const { id, profilePath, memberId, nickname, totalLikeCount, isLike, imagePath, content } = post;

  const POST_DETAIL = `${POST}/${id}`;
  const MEMBER_DETAIL = `${MEMBER}/${memberId}`;

  const { addLikeMutate } = useAddLike();
  const { cancelLikeMutate } = useCacncelLike();

  /** 좋아요 핸들링 */
  const handleLike = (id: number) => {
    if (!isLike) {
      addLikeMutate(id, {
        onSuccess: () => {
          setPost((prev) => ({ ...prev, isLike: true, totalLikeCount: prev.totalLikeCount + 1 }));
        },
      });
      return;
    }

    cancelLikeMutate(id, {
      onSuccess: () => {
        setPost((prev) => ({ ...prev, isLike: false, totalLikeCount: prev.totalLikeCount - 1 }));
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
        <Typography variant="sh4">좋아요 {totalLikeCount}개</Typography>
        <Typography variant="b3">
          <b>{nickname}</b> {content}
        </Typography>
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
