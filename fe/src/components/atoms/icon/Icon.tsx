import { HTMLAttributes } from 'react';
import { IconName, icons } from './icons';

interface Props extends HTMLAttributes<HTMLOrSVGElement> {
  size?: 'small' | 'medium' | 'large' | 'xlarge';
  name: IconName;
  fill?: string;
  stroke?: string;
  style?: React.CSSProperties;
}

export default function Icon({
  size = 'medium',
  name,
  fill,
  stroke,
  style,
  ...htmlSvgAttributes
}: Props) {
  const Component = icons[name] || null;

  const sizeMap = {
    small: 16,
    medium: 24,
    large: 32,
    xlarge: 96,
  };

  const width = sizeMap[size];
  const height = sizeMap[size];

  return <Component {...htmlSvgAttributes} style={{ width, height, fill, stroke, ...style }} />;
}
