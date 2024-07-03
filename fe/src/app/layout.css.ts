import { style } from '@vanilla-extract/css';

export const containerStyle = style({
  maxWidth: 540,
  margin: '0 auto',
  minHeight: '100vh',

  display: 'flex',
  flexDirection: 'column',
});
