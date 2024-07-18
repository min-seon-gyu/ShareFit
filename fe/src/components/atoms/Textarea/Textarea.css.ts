import { Radius } from '@/styles/Radius';
import { style } from '@vanilla-extract/css';

export const textareaStyle = style({
  width: '100%',
  resize: 'none',
  padding: '6px 8px',
  borderRadius: Radius.MEDIUM,
  fontSize: 14,

  ':focus': {
    outline: 'none',
  },
});
