export interface GetKakaoTokenReqeustDto {
  code: string;
}

export interface GetKakaoInfoRequestDto {
  accessToken: string;
}

export interface LoginRequestDto {
  uuid: string;
  nickname: string;
  profilePath: string;
}

export interface LoginResponseDto {
  accessToken: string;
}
