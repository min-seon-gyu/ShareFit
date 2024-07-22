import { style } from '@vanilla-extract/css';

export const headerStyle = style({
  padding: '24px 12px 8px',
});

export const profileStyle = style({
  display: 'flex',
  alignItems: 'center',
  gap: 48,
});

export const itemStyle = style({
  textAlign: 'center',

  display: 'flex',
  flexDirection: 'column',
  gap: 2,
});
