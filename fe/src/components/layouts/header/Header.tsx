'use client';

import { Typography } from '@/components/atoms/Typography';
import { buttonWrapStyle, headerStyle, tempButtonStyle } from './Header.css';
import { useCheckScrollToTop } from '@/hooks/interaction/useCheckScrollTop';
import { black, white } from '@/styles/Color';

export default function Header() {
  const isScrollTop = useCheckScrollToTop();

  return (
    <div className={headerStyle({ isScrollTop })}>
      <Typography as="h1" variant="h5" color={isScrollTop ? white : black}>
        ShareFit
      </Typography>

      <div className={buttonWrapStyle}>
        <button className={tempButtonStyle({ isScrollTop })} />
        <button className={tempButtonStyle({ isScrollTop })} />
      </div>
    </div>
  );
}
