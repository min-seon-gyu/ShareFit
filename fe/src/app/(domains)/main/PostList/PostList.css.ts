import { mq } from '@/styles/Breakpoint';
import { style } from '@vanilla-extract/css';

export const postListStyle = style({
  padding: 12,

  display: 'grid',
  gridTemplateColumns: 'repeat(2, 1fr)',
  gap: 8,

  '@media': {
    [mq['xs']]: {
      gridTemplateColumns: '1fr',
    },
  },
});

export const imgWrapStyle = style({
  position: 'relative',
  aspectRatio: '3 / 4',
});

export const descriptionStyle = style({
  marginTop: 4,
});
