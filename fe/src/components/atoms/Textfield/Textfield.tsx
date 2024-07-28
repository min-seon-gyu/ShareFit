import { forwardRef, HTMLAttributes, Ref } from 'react';
import * as styles from './Textfield.css';

interface Props extends HTMLAttributes<HTMLInputElement> {
  value: string;
  placeholder?: string;
}

const _Textfield = (
  { value, placeholder, ...htmlInputAttributes }: Props,
  ref: Ref<HTMLInputElement>,
) => {
  return (
    <input
      value={value}
      placeholder={placeholder}
      className={styles.textfield}
      ref={ref}
      {...htmlInputAttributes}
    />
  );
};

export const Textfield = forwardRef(_Textfield);
