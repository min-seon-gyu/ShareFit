import Image from 'next/image';
import { descriptionStyle, imgWrapStyle, postListStyle } from './PostList.css';
import ExampleImg from '@/assets/images/example.jpg';
import Link from 'next/link';
import { Typography } from '@/components/atoms/Typography';
import { gray } from '@/styles/Color';

/** Server Component */
export default function PostList() {
  return (
    <div className={postListStyle}>
      {Array.from({ length: 10 }).map((_, idx) => (
        <div key={idx}>
          <Link href="/" key={idx}>
            <div className={imgWrapStyle}>
              <Image layout="fill" src={ExampleImg} alt="example" />
            </div>
          </Link>

          <div className={descriptionStyle}>
            <Typography variant="b5" color={gray.gray6}>
              2024.07.03 · 조회 234
            </Typography>
          </div>
        </div>
      ))}
    </div>
  );
}
