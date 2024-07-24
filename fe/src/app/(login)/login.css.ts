import { black } from '@/styles/Color';
import { style } from '@vanilla-extract/css';

export const page = style({
  flex: 1,
  background: black,
  color: 'white',
  textAlign: 'center',

  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  alignItems: 'center',
});
