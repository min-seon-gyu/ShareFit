import Plus from './assets/plus.svg';
import User from './assets/user.svg';
import Upload from './assets/upload.svg';
import Heart from './assets/heart.svg';
import Chat from './assets/chat.svg';

export const icons = {
  'plus': Plus,
  'user': User,
  'upload': Upload,
  'heart': Heart,
  'chat': Chat,
};

export type IconName = keyof typeof icons;
