import { style } from '@vanilla-extract/css';

export const header = style({
  padding: '6px 8px',

  display: 'flex',
  columnGap: 12,
  alignItems: 'center',
});

export const imageWrap = style({
  position: 'relative',
  width: '100%',
  aspectRatio: '3 / 4',
});

export const content = style({
  padding: '6px 8px',

  display: 'flex',
  flexDirection: 'column',
  gap: 4,
});

export const iconWrap = style({
  display: 'flex',
  gap: 6,
});

export const icon = style({
  cursor: 'pointer',

  ':hover': {
    opacity: 0.7,
  },
});
