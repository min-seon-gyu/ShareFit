import { gray } from '@/styles/Color';
import { Radius } from '@/styles/Radius';
import { style } from '@vanilla-extract/css';

export const textfield = style({
  padding: '8px 12px',
  borderRadius: Radius.MEDIUM,
  border: `1px solid ${gray.gray6}`,
  color: gray.gray6,
  fontSize: '14px',
  lineHeight: '20px',

  ':focus': {
    outline: 'none',
  },
});
