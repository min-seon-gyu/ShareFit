import { style } from '@vanilla-extract/css';

export const content = style({
  display: 'grid',
  gridTemplateColumns: 'repeat(3, 1fr)',
  gap: 2,
});

export const imageWrap = style({
  aspectRatio: '1 / 1',
  position: 'relative',
  width: '100%',
});
