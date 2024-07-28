import { style } from '@vanilla-extract/css';

export const wrap = style({
  marginTop: 8,

  display: 'flex',
  flexDirection: 'column',
  gap: 4,
});

export const commentWrap = style({
  display: 'flex',
  alignItems: 'center',
  gap: 12,
});

export const inputWrap = style({
  marginTop: 8,

  display: 'flex',
  gap: 4,
});
