import { AxiosError, AxiosResponse } from 'axios';
import API from '..';
import {
  CreatePostRequestDto,
  CreatePostResponseDto,
  UploadImageRequestDto,
  UploadImageResponseDto,
} from './types';
import { ApiResponse } from '../types';

/** 이미지 업로드 api */
export const uploadImageApi = async ({
  formData,
}: UploadImageRequestDto): Promise<
  AxiosResponse<ApiResponse<UploadImageResponseDto>, AxiosError>
> => {
  return await API.post('/post/image', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
};

/** 게시글 등록 api */
export const createPostApi = async ({
  content,
  imagePath,
}: CreatePostRequestDto): Promise<
  AxiosResponse<ApiResponse<CreatePostResponseDto>, AxiosError>
> => {
  return await API.post('/post', { content, imagePath });
};
