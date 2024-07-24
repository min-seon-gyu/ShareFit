import { style } from '@vanilla-extract/css';

export const header = style({
  padding: '24px 12px',
});

export const profile = style({
  display: 'flex',
  alignItems: 'center',
  gap: 48,
});

export const item = style({
  textAlign: 'center',

  display: 'flex',
  flexDirection: 'column',
  gap: 2,
});

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
