'use client';

import { Button } from '@/components/atoms/Button';
import { imageWrapStyle, pageStyle } from './page.css';
import { Textarea } from '@/components/atoms/Textarea';
import { useState } from 'react';
import { FileUploader, FileUploaderProvider } from '@/components/atoms/FileUploader';
import Image from 'next/image';
import { useCreatePost, useUploadImage } from '@/apis/post';
import { useRouter } from 'next/navigation';
import { MAIN } from '@/constants/routes';

export default function Edit() {
  const router = useRouter();

  const [content, setContent] = useState('');
  const [imagePath, setImagePath] = useState('');

  const { uploadImageMutate } = useUploadImage();
  const { createPostMutate } = useCreatePost();

  const handleContentChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setContent(e.target.value);
  };

  const handleFileChange = async (file: File) => {
    const formData = new FormData();
    formData.append('image', file);

    uploadImageMutate(
      { formData },
      {
        onSuccess: (res) => {
          setImagePath(res.data.data.imagePath);
        },
      },
    );
  };

  const handleFormSubmit = async () => {
    createPostMutate(
      { content, imagePath },
      {
        onSuccess: () => {
          router.push(MAIN);
        },
      },
    );
  };

  return (
    <div className={pageStyle}>
      {imagePath ? (
        <div className={imageWrapStyle}>
          <Image src={imagePath} layout="fill" alt="preview-img" />
        </div>
      ) : (
        <FileUploaderProvider handleFile={handleFileChange}>
          <FileUploader description="업로드할 이미지를 업로드해주세요 (3:4 비율 권장)" />
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
