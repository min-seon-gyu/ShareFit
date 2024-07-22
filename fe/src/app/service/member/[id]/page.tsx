import { getMemberApi } from '@/apis/member';
import Image from 'next/image';
import { headerStyle, itemStyle, profileImgStyle, profileStyle } from './page.css';
import { Typography } from '@/components/atoms/Typography';

type Props = {
  params: {
    id: string;
  };
};

const fetchMemberData = async (id: number) => {
  const response = await getMemberApi(id);

  return response.data.data;
};

export default async function Member({ params: { id } }: Props) {
  const { nickname, profilePath } = await fetchMemberData(Number(id));

  return (
    <div>
      <div className={headerStyle}>
        <div className={profileStyle}>
          <div>
            <div className={profileImgStyle}>
              {profilePath && <Image src={profilePath} layout="fill" alt="post-img" />}
            </div>
          </div>

          <div className={itemStyle}>
            <Typography variant="sh4">0</Typography>
            <Typography>게시물</Typography>
          </div>

          <div className={itemStyle}>
            <Typography variant="sh4">0</Typography>
            <Typography>팔로워</Typography>
          </div>

          <div className={itemStyle}>
            <Typography variant="sh4">0</Typography>
            <Typography>팔로잉</Typography>
          </div>
        </div>

        <Typography style={{ marginTop: 8 }} variant="b4">
          {nickname}
        </Typography>
      </div>
    </div>
  );
}
