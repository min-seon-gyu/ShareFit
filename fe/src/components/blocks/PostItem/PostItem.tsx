import { Post } from '@/apis/post';
import * as styles from './PostItem.css';
import { ProfileIcon } from '@/components/atoms/ProfileIcon';
import { Typography } from '@/components/atoms/Typography';
import Image from 'next/image';
import Icon from '@/components/atoms/icon/Icon';
import { black, red } from '@/styles/Color';
import Link from 'next/link';
import { MEMBER, POST } from '@/constants/routes';

type Props = {
  data: Post;
};

export function PostItem({ data }: Props) {
  const { id, profilePath, memberId, nickname, likes, imagePath, content } = data;

  const POST_DETAIL = `${POST}/${id}`;
  const MEMBER_DETAIL = `${MEMBER}/${memberId}`;

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
          <HeartButton isChecked={true} />
          <Link href={POST_DETAIL}>
            <Icon className={styles.icon} name="chat" fill={black} />
          </Link>
        </div>
        <Typography variant="sh4">좋아요 {likes}개</Typography>
        <Typography variant="b3">
          <b>{nickname}</b> {content}
        </Typography>
      </div>
    </div>
  );
}

function HeartButton({ isChecked }: { isChecked: boolean }) {
  return (
    <Icon
      className={styles.icon}
      name="heart"
      stroke={isChecked ? red.red3 : black}
      fill={isChecked ? red.red3 : 'transparent'}
    />
  );
}
