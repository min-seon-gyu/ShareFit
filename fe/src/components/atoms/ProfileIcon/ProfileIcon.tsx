import Image from 'next/image';
import * as styles from './ProfileIcon.css';
import Link from 'next/link';
import { MEMBER } from '@/constants/routes';

type Props = {
  size?: 'medium' | 'large';
  profilePath?: string;
  id: number;
};

const DEFAULT_IMG = 'http://t1.kakaocdn.net/account_images/default_profile.jpeg.twg.thumb.R640x640';

const _ProfileIcon = ({ size = 'medium', profilePath = DEFAULT_IMG, id }: Props) => {
  return (
    <Link href={`${MEMBER}/${id}`}>
      <div className={styles.imgWrap({ size })}>
        <Image src={profilePath} layout="fill" alt="profile-img" />
      </div>
    </Link>
  );
};

export const ProfileIcon = _ProfileIcon;
