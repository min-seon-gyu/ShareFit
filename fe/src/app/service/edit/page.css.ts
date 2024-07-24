import { style } from '@vanilla-extract/css';

export const page = style({
  padding: 12,

  display: 'grid',
  gap: 12,
});

export const imageWrap = style({
  position: 'relative',
  width: '100%',
  aspectRatio: '3 / 4',
});
