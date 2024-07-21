import { getPostApi } from '@/apis/post';
import Image from 'next/image';
import { imageWrapStyle, profileImgStyle, profileStyle } from './page.css';
import { Typography } from '@/components/atoms/Typography';

type Props = {
  params: {
    id: string;
  };
};

const fetchPostDetail = async (id: number) => {
  const response = await getPostApi(id);

  return response.data.data;
};

export default async function PostDetail({ params: { id } }: Props) {
  const data = await fetchPostDetail(Number(id));

  return (
    <div>
      <div className={imageWrapStyle}>
        <Image src={data.imagePath} layout="fill" alt="post-img" />
      </div>

      <div className={profileStyle}>
        <div className={profileImgStyle} />
        <Typography variant="sh3">닉네임</Typography>
      </div>

      <Typography
        style={{
          padding: '0 12px',
        }}
        variant="b3">
        {data.content}
      </Typography>
    </div>
  );
}
