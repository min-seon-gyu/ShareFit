'use client';

import { HTMLAttributes, useContext, useRef, useState } from 'react';
import * as styles from './FileUploader.css';
import Icon from '../icon/Icon';
import { black, gray } from '@/styles/Color';
import { Typography } from '../Typography';
import { FileUploaderContext } from './FileUploader.provider';

interface Props extends HTMLAttributes<HTMLDivElement> {
  description?: string;
}

export function FileUploader({ description, ...htmlAttributes }: Props) {
  const context = useContext(FileUploaderContext);

  const componentRef = useRef<HTMLDivElement>(null);
  const inputRef = useRef<HTMLInputElement>(null);

  const [isDragging, setIsDragging] = useState(false);

  if (context === null) {
    throw new Error('FileUploaderContext is not provided');
  }

  const handleInputOpen = () => {
    if (inputRef.current) {
      inputRef.current.click();
    }
  };

  const handleDragEnter = (e: React.DragEvent<HTMLDivElement>) => {
    e.preventDefault();
    setIsDragging(true);
  };

  const handleDragLeave = (e: React.DragEvent<HTMLDivElement>) => {
    e.preventDefault();
    setIsDragging(false);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { files } = e.target;
    if (files && files.length > 0) {
      context.handleFile(files[0]);
    }
  };

  const mainColor = isDragging ? black : gray.gray6;

  return (
    <div
      className={styles.container({ isDragging })}
      ref={componentRef}
      onClick={handleInputOpen}
      onDragEnter={handleDragEnter}
      onDragLeave={handleDragLeave}
      {...htmlAttributes}>
      <Icon name="upload" size="xlarge" fill={mainColor} />

      <Typography variant="b3" color={mainColor}>
        {description}
      </Typography>

      <input
        style={{ display: 'none' }}
        onChange={handleInputChange}
        ref={inputRef}
        type="file"
        accept=".png, .jpeg, .jpg"
      />
    </div>
  );
}
