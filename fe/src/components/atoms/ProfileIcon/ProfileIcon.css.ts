import { Radius } from '@/styles/Radius';
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
      small: {
        width: 32,
        height: 32,
      },
      medium: {
        width: 40,
        height: 40,
      },
      large: {
        width: 72,
        height: 72,
      },
    },
  },
});
