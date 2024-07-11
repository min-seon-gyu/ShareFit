'use client';

import { Typography } from '@/components/atoms/Typography';
import { buttonWrapStyle, headerStyle, iconButtonStyle } from './Header.css';
import { useCheckScrollToTop } from '@/hooks/interaction/useCheckScrollTop';
import { black, white } from '@/styles/Color';
import Icon from '@/components/atoms/icon/Icon';

export default function Header() {
  const isScrollTop = useCheckScrollToTop();
  const mainColor = isScrollTop ? white : black;

  return (
    <div className={headerStyle({ isScrollTop })}>
      <Typography as="h1" variant="h5" color={mainColor}>
        ShareFit
      </Typography>

      <div className={buttonWrapStyle}>
        <div className={iconButtonStyle}>
          <Icon name="plus" fill={mainColor} />
        </div>
        <div className={iconButtonStyle}>
          <Icon name="user" stroke={mainColor} />
        </div>
      </div>
    </div>
  );
}
