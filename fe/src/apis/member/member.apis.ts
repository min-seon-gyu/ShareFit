import { AxiosError, AxiosResponse } from 'axios';
import API from '..';
import { ApiResponse } from '../types';
import { GetMemberResponseDto, GetMyInfoResponseDto } from './member.types';

/** 자신 정보 조회 api */
export const getMyInfoApi = async (): Promise<
  AxiosResponse<ApiResponse<GetMyInfoResponseDto>, AxiosError>
> => {
  return await API.get('/member');
};

/** 회원 조회 api */
export const getMemberApi = async (
  id: number,
): Promise<AxiosResponse<ApiResponse<GetMemberResponseDto>, AxiosError>> => {
  return await API.get(`/member/${id}`);
};
