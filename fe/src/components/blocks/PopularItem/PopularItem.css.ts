import { Radius } from '@/styles/Radius';
import { style } from '@vanilla-extract/css';

export const wrap = style({
  textAlign: 'center',

  display: 'grid',
  gap: 8,
});

export const imgWrap = style({
  width: 72,
  height: 72,
  cursor: 'pointer',
  overflow: 'hidden',
  borderRadius: Radius.MAXIMUM,

  position: 'relative',

  ':hover': {
    opacity: 0.8,
  },
});
