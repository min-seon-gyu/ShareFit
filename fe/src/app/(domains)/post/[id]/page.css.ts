import { gray } from '@/styles/Color';
import { Radius } from '@/styles/Radius';
import { style } from '@vanilla-extract/css';

export const imageWrapStyle = style({
  position: 'relative',
  width: '100%',
  aspectRatio: '3 / 4',
});

export const profileStyle = style({
  padding: 12,

  display: 'flex',
  alignItems: 'center',
  columnGap: 12,
});

export const profileImgStyle = style({
  width: 32,
  height: 32,
  borderRadius: Radius.MAXIMUM,
  background: gray.gray3,
});
