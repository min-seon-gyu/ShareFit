'use client';

import { Button } from '@/components/atoms/Button';
import { imageWrapStyle, pageStyle } from './page.css';
import { Textarea } from '@/components/atoms/Textarea';
import { useState } from 'react';
import { FileUploader, FileUploaderProvider } from '@/components/atoms/FileUploader';
import Image from 'next/image';
import { createPostApi, uploadImageApi } from '@/apis/post';

export default function Edit() {
  const [content, setContent] = useState('');
  const [imagePath, setImagePath] = useState('');

  const handleContentChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setContent(e.target.value);
  };

  const handleFileChange = async (file: File) => {
    const formData = new FormData();
    formData.append('image', file);

    const res = await uploadImageApi({ formData });

    setImagePath(res.data.data.imagePath);
  };

  const handleFormSubmit = async () => {
    const res = await createPostApi({ content, imagePath });
    console.log(res);
  };

  return (
    <div className={pageStyle}>
      {imagePath ? (
        <div className={imageWrapStyle}>
          <Image src={imagePath} layout="fill" alt="preview-img" />
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
      <Button text="공유하기" fullWidth onClick={handleFormSubmit} />
    </div>
  );
}
