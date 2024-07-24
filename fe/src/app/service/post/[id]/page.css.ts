import { gray } from '@/styles/Color';
import { Radius } from '@/styles/Radius';
import { style } from '@vanilla-extract/css';

export const imageWrap = style({
  position: 'relative',
  width: '100%',
  aspectRatio: '3 / 4',
});

export const profile = style({
  display: 'flex',
  alignItems: 'center',
  columnGap: 12,
});

export const profileImg = style({
  width: 32,
  height: 32,
  borderRadius: Radius.MAXIMUM,
  background: gray.gray3,
});

export const content = style({
  padding: 12,
});
