import { headerStyle, itemStyle, profileStyle } from './page.css';
import { Typography } from '@/components/atoms/Typography';
import { cookies } from 'next/headers';
import { GetMemberResponseDto } from '@/apis/member/member.types';
import { ApiResponse } from '@/apis/types';
import { ProfileIcon } from '@/components/atoms/ProfileIcon';

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

export default async function Member({ params: { id } }: Props) {
  const { nickname, profilePath, id: memberId } = await fetchMemberData(Number(id));

  return (
    <div>
      <div className={headerStyle}>
        <div className={profileStyle}>
          <ProfileIcon size="large" profilePath={profilePath ?? undefined} id={memberId} />

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
