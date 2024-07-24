import { style } from '@vanilla-extract/css';

export const container = style({
  maxWidth: 480,
  margin: '0 auto',
  minHeight: '100vh',

  display: 'flex',
  flexDirection: 'column',
});
