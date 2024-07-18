import Plus from './assets/plus.svg';
import User from './assets/user.svg';
import Upload from './assets/upload.svg';

export const icons = {
  'plus': Plus,
  'user': User,
  'upload': Upload,
};

export type IconName = keyof typeof icons;
