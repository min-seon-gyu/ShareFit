import { gray } from '@/styles/Color';
import { Radius } from '@/styles/Radius';
import { style } from '@vanilla-extract/css';

export const headerStyle = style({
  padding: '24px 12px 8px',
});

export const profileStyle = style({
  display: 'flex',
  alignItems: 'center',
  gap: 48,
});

export const profileImgStyle = style({
  width: 72,
  height: 72,
  borderRadius: Radius.MAXIMUM,
  background: gray.gray3,

  position: 'relative',
});

export const itemStyle = style({
  textAlign: 'center',

  display: 'flex',
  flexDirection: 'column',
  gap: 2,
});
