import * as styles from './page.css';
import { Typography } from '@/components/atoms/Typography';
import { cookies } from 'next/headers';
import { GetMemberResponseDto } from '@/apis/member/member.types';
import { ApiResponse } from '@/apis/types';
import { ProfileIcon } from '@/components/atoms/ProfileIcon';
import { GetPostsResponseDto } from '@/apis/post';
import Image from 'next/image';
import Link from 'next/link';
import { POST } from '@/constants/routes';
import { MemberImageLoader } from '@/components/blocks/MemberImageLoader';
import { RefreshHandler } from '@/components/layouts/RefreshHandler';

type Props = {
  params: {
    id: string;
  };
};

const fetchMemberData = async (id: number): Promise<GetMemberResponseDto> => {
  const cookieStore = cookies();
  const accessToken = cookieStore.get('accessToken')?.value;

  const response = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/member/${id}`, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  const responseData: ApiResponse = await response.json();

  return responseData.data;
};

const fetchPostsData = async (uuid: string): Promise<GetPostsResponseDto> => {
  const cookieStore = cookies();
  const accessToken = cookieStore.get('accessToken')?.value;

  const response = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/posts?uuid=${uuid}`, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  const responseData: ApiResponse = await response.json();

  return responseData.data;
};

export default async function Member({ params: { id } }: Props) {
  const { nickname, profilePath, id: memberId, uuid } = await fetchMemberData(Number(id));
  const { totalCount, totalPages, posts } = await fetchPostsData(uuid);

  return (
    <div>
      <div className={styles.header}>
        <div className={styles.profile}>
          <ProfileIcon size="large" profilePath={profilePath ?? undefined} id={memberId} />

          <div className={styles.item}>
            <Typography variant="sh4">{totalCount}</Typography>
            <Typography>게시물</Typography>
          </div>
        </div>

        <Typography style={{ marginTop: 8 }} variant="b4">
          {nickname}
        </Typography>
      </div>

      <div className={styles.content}>
        {posts.map((post) => (
          <Link href={`${POST}/${post.id}`} key={post.id}>
            <div className={styles.imageWrap}>
              <Image src={post.imagePath} alt={`post-img-${post.id}`} layout="fill" />
            </div>
          </Link>
        ))}
      </div>

      <MemberImageLoader totalPages={totalPages} uuid={uuid} />
      <RefreshHandler />
    </div>
  );
}
