import { black, white } from '@/styles/Color';
import { Shadow } from '@/styles/Shadow';
import { style } from '@vanilla-extract/css';
import { recipe } from '@vanilla-extract/recipes';

export const headerStyle = recipe({
  base: {
    width: '100%',
    maxWidth: 540,
    height: 52,
    padding: '0 12px',
    margin: '0 auto',
    zIndex: 999,
    transition: 'all 0.3s ease-in-out',

    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',

    position: 'fixed',
    top: 0,
    left: 0,
    right: 0,
  },

  variants: {
    isScrollTop: {
      true: {
        background: black,
        boxShadow: 'none',
      },
      false: {
        background: white,
        boxShadow: Shadow.MEDIUM,
      },
    },
  },
});

export const buttonWrapStyle = style({
  display: 'flex',
  alignItems: 'center',
  gap: 12,
});

export const iconButtonStyle = style({
  cursor: 'pointer',
  display: 'flex',
});
