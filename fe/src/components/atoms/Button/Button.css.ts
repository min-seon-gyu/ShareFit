import { black, white } from '@/styles/Color';
import { Radius } from '@/styles/Radius';
import { recipe } from '@vanilla-extract/recipes';

export const button = recipe({
  base: {
    padding: '10px 12px',
    fontSize: 14,
    lineHeight: '20px',
    background: black,
    color: white,
    borderRadius: Radius.MEDIUM,
    border: 'none',
    cursor: 'pointer',

    ':hover': {
      opacity: 0.9,
    },
    ':active': {
      opacity: 0.7,
    },
  },

  variants: {
    fullWidth: {
      true: {
        width: '100%',
      },
    },
  },
});
