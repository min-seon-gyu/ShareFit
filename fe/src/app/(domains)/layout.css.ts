import { black, gray } from '@/styles/Color';
import { style } from '@vanilla-extract/css';

export const pageStyle = style({
  flex: 1,
  minHeight: '200vh',
  paddingTop: 64,
  borderLeft: `1px solid ${gray.gray3}`,
  borderRight: `1px solid ${gray.gray3}`,
});
