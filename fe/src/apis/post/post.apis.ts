import { AxiosError, AxiosResponse } from 'axios';
import API from '..';
import {
  CreatePostRequestDto,
  CreatePostResponseDto,
  GetPostResponseDto,
  GetPostsParams,
  GetPostsResponseDto,
  UpdatePostRequestDto,
  UploadImageRequestDto,
  UploadImageResponseDto,
} from './post.types';
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

/** 게시글 업데이트 api */
export const updatePostApi = async ({
  id,
  content,
  imagePath,
}: UpdatePostRequestDto): Promise<AxiosResponse<ApiResponse, AxiosError>> => {
  return await API.patch('/post', { id, content, imagePath });
};

/** 게시글 조회 api */
export const getPostApi = async (
  id: number,
): Promise<AxiosResponse<ApiResponse<GetPostResponseDto>, AxiosError>> => {
  return await API.get(`/post/${id}`);
};

/** 게시글 삭제 api */
export const deletePostApi = async (
  id: number,
): Promise<AxiosResponse<ApiResponse, AxiosError>> => {
  return await API.delete(`/post/${id}`);
};

/** 게시글 리스트 조회 api */
export const getPostsApi = async ({
  uuid,
  page,
}: GetPostsParams): Promise<AxiosResponse<ApiResponse<GetPostsResponseDto>, AxiosError>> => {
  return await API.get('/post', {
    params: { uuid, page },
  });
};
