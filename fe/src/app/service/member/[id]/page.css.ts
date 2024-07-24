import { style } from '@vanilla-extract/css';

export const header = style({
  padding: '24px 12px 8px',
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
