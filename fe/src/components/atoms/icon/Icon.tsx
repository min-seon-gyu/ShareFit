import { IconName, icons } from './icons';

type Props = {
  size?: 'medium';
  name: IconName;
  fill?: string;
  stroke?: string;
  style?: React.CSSProperties;
};

export default function Icon({ size = 'medium', name, fill, stroke, style }: Props) {
  const Component = icons[name] || null;

  const sizeMap = {
    medium: 24,
  };

  const width = sizeMap[size];
  const height = sizeMap[size];

  return <Component style={{ width, height, fill, stroke, ...style }} />;
}
