import { GetPostResponseDto } from '@/apis/post';
import Image from 'next/image';
import * as styles from './page.css';
import { Typography } from '@/components/atoms/Typography';
import { cookies } from 'next/headers';
import { ApiResponse } from '@/apis/types';

type Props = {
  params: {
    id: string;
  };
};

const fetchPostData = async (id: number): Promise<GetPostResponseDto> => {
  const cookieStore = cookies();
  const accessToken = cookieStore.get('accessToken')?.value;

  const response = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/post/${id}`, {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  const responseData: ApiResponse = await response.json();

  return responseData.data;
};

export default async function PostDetail({ params: { id } }: Props) {
  const {
    id: postId,
    imagePath,
    content,
    likes,
    memberId,
    nickname,
    profilePath,
  } = await fetchPostData(Number(id));

  return (
    <div>
      <div className={styles.imageWrap}>
        <Image src={imagePath} layout="fill" alt="post-img" />
      </div>

      <div className={styles.content}>
        <div className={styles.profile}>
          <div className={styles.profileImg} />
          <Typography variant="sh3">닉네임</Typography>
        </div>

        <Typography style={{ marginTop: 4 }} variant="b4">
          <b>{likes}명</b>이 좋아합니다
        </Typography>

        <Typography style={{ marginTop: 8 }} variant="b3">
          {content}
        </Typography>
      </div>
    </div>
  );
}
