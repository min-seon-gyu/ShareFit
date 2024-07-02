import { black, white } from '@/styles/Color';
import { Radius } from '@/styles/Radius';
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
        background: 'transparent',
        boxShadow: Shadow.MEDIUM,
      },
    },
  },
});

export const buttonWrapStyle = style({
  display: 'flex',
  gap: 8,
});

export const tempButtonStyle = recipe({
  base: {
    width: 32,
    height: 32,
    border: 'none',
    borderRadius: Radius.MAXIMUM,
    cursor: 'pointer',
  },

  variants: {
    isScrollTop: {
      true: {
        background: white,
      },
      false: {
        background: black,
      },
    },
  },
});
