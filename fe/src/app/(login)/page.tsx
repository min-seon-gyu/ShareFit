import { Typography } from '@/components/atoms/Typography';
import * as styles from './login.css';
import { white } from '@/styles/Color';
import { KakaoLoginButton } from './KakaoLoginButton';

export default function Login() {
  return (
    <div className={styles.page}>
      <Typography as="h1" color={white} variant="h2">
        ShareFit
      </Typography>
      <Typography as="h3" color={white} variant="sh4">
        오늘의 스타일을 세상과 공유하다
      </Typography>

      <div style={{ marginTop: 24 }}>
        <KakaoLoginButton />
      </div>
    </div>
  );
}
