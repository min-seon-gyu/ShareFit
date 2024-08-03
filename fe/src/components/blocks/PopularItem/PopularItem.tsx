import { GetPopularPostsResponseDto } from '@/apis/post';
import * as styles from './PopularItem.css';
import Image from 'next/image';
import Link from 'next/link';
import { POST } from '@/constants/routes';
import { Typography } from '@/components/atoms/Typography';

type Props = {
  data: GetPopularPostsResponseDto['posts'][0];
};

export function PopularItem({ data }: Props) {
  const { imagePath, id, nickname } = data;

  return (
    <div className={styles.wrap}>
      <Link href={`${POST}/${id}`}>
        <div className={styles.imgWrap}>
          <Image layout="fill" src={imagePath} alt={`popular-img-${id}`} />
        </div>
      </Link>

      <Typography variant="sh4">{nickname}</Typography>
    </div>
  );
}
