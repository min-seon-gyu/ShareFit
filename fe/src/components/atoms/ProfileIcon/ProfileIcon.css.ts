import { Radius } from '@/styles/Radius';
import { style } from '@vanilla-extract/css';
import { recipe } from '@vanilla-extract/recipes';

export const imgWrap = recipe({
  base: {
    borderRadius: Radius.MAXIMUM,
    overflow: 'hidden',
    cursor: 'pointer',

    position: 'relative',
  },

  variants: {
    size: {
      medium: {
        width: 32,
        height: 32,
      },
      large: {
        width: 72,
        height: 72,
      },
    },
  },
});
