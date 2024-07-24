'use client';

import { Typography } from '@/components/atoms/Typography';
import * as styles from './Header.css';
import { useCheckScrollToTop } from '@/hooks/interaction/useCheckScrollTop';
import { black, white } from '@/styles/Color';
import Icon from '@/components/atoms/icon/Icon';
import { useRouter } from 'next/navigation';
import { EDIT, POST } from '@/constants/routes';
import { GetMyInfoResponseDto } from '@/apis/member/member.types';
import { ProfileIcon } from '@/components/atoms/ProfileIcon';

type Props = {
  data: GetMyInfoResponseDto;
};

export default function Header({ data }: Props) {
  const router = useRouter();

  const isScrollTop = useCheckScrollToTop();
  const mainColor = isScrollTop ? white : black;

  return (
    <div className={styles.header({ isScrollTop })}>
      <Typography
        style={{ cursor: 'pointer' }}
        as="h1"
        variant="h5"
        color={mainColor}
        onClick={() => router.push(POST)}>
        ShareFit
      </Typography>

      <div className={styles.buttonWrap}>
        <div className={styles.iconButton} onClick={() => router.push(EDIT)}>
          <Icon name="plus" fill={mainColor} />
        </div>
        <ProfileIcon profilePath={data.profilePath ?? undefined} id={data.id} />
      </div>
    </div>
  );
}
