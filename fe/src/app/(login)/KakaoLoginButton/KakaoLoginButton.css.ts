import { black } from '@/styles/Color';
import { Radius } from '@/styles/Radius';
import { style } from '@vanilla-extract/css';

export const kakaoLoginButton = style({
  width: 183,
  height: 45,
  color: black,
  background: '#FAE100',
  borderRadius: Radius.MEDIUM,
  border: 'none',
  cursor: 'pointer',
  position: 'relative',

  display: 'flex',
  columnGap: 8,

  ':hover': {
    opacity: 0.7,
  },

  ':active': {
    opacity: 0.5,
  },
});
