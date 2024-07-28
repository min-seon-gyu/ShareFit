export interface CreateCommentRequestDto {
  id: number;
  content: string;
}

export interface CreateCommentResponseDto {
  id: number;
  content: string;
  memberId: number;
  nickname: string;
  profilePath: string;
}
