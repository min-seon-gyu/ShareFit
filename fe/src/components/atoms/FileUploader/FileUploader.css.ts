import { black, gray } from '@/styles/Color';
import { Radius } from '@/styles/Radius';
import { recipe } from '@vanilla-extract/recipes';

export const container = recipe({
  base: {
    padding: 24,
    borderRadius: Radius.MEDIUM,
    border: `2px dashed ${gray.gray3}`,
    cursor: 'pointer',

    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    gap: 8,

    aspectRatio: '3 / 4',
  },

  variants: {
    isDragging: {
      true: {
        borderColor: black,
      },
    },
  },
});
