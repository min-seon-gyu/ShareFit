import { style } from '@vanilla-extract/css';

export const popularSection = style({
  padding: '6px 8px',

  display: 'grid',
  rowGap: 12,
});

export const popularItemWrap = style({
  display: 'flex',
  justifyContent: 'space-evenly',
});
