'use client';

import { Button } from '@/components/atoms/Button';
import { imageWrapStyle, pageStyle } from './page.css';
import { Textarea } from '@/components/atoms/Textarea';
import { useState } from 'react';
import { FileUploader, FileUploaderProvider } from '@/components/atoms/FileUploader';
import Image from 'next/image';

export default function Edit() {
  const [content, setContent] = useState('');
  const [img, setImg] = useState('');

  const handleContentChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setContent(e.target.value);
  };

  const handleFileChange = (file: File) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = () => {
      setImg(reader.result as string);
    };
  };

  return (
    <div className={pageStyle}>
      {img ? (
        <div className={imageWrapStyle}>
          <Image src={img} layout="fill" alt="preview-img" />
        </div>
      ) : (
        <FileUploaderProvider handleFile={handleFileChange}>
          <FileUploader description="이미지를 업로드해주세요 (~~~비율 설명)" />
        </FileUploaderProvider>
      )}
      <Textarea
        value={content}
        placeholder="내용을 입력해주세요."
        height={140}
        onChange={handleContentChange}
      />
      <Button text="공유하기" fullWidth />
    </div>
  );
}
