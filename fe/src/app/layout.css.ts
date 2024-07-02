import { black } from '@/styles/Color';
import { Radius } from '@/styles/Radius';
import { style } from '@vanilla-extract/css';

export const containerStyle = style({
  maxWidth: '600px',
  margin: '0 auto',
  minHeight: '100vh',

  display: 'flex',
  flexDirection: 'column',
});

export const KakaoLoginButtonStyle = style({
  width: '183px',
  padding: '9px 12px',
  color: black,
  background: '#FAE100',
  borderRadius: Radius.MEDIUM,
  border: 'none',
  cursor: 'pointer',

  display: 'flex',
  columnGap: 8,

  ':hover': {
    opacity: 0.7,
  },

  ':active': {
    opacity: 0.5,
  },
});
