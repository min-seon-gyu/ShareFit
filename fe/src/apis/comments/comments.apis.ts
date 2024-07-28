import { AxiosError, AxiosResponse } from 'axios';
import API from '..';
import { ApiResponse } from '../types';
import { CreateCommentRequestDto, CreateCommentResponseDto } from './comments.type';

/** 댓글 추가 api */
export const createCommentApi = async ({
  id,
  content,
}: CreateCommentRequestDto): Promise<
  AxiosResponse<ApiResponse<CreateCommentResponseDto>, AxiosError>
> => {
  return await API.post('/comment', { id, content });
};

/** 댓글 삭제 api */
export const deleteCommentApi = async (id: number) => {
  return await API.delete(`/comment/${id}`);
};
