import Plus from './assets/plus.svg';
import User from './assets/user.svg';

export const icons = {
  'plus': Plus,
  'user': User,
};

export type IconName = keyof typeof icons;
