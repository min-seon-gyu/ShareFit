export interface UploadImageRequestDto {
  formData: FormData;
}

export interface UploadImageResponseDto {
  imagePath: string;
}

export interface CreatePostRequestDto {
  content: string;
  imagePath: string;
}

export interface CreatePostResponseDto {
  id: number;
  content: string;
  imagePath: string;
  likes: number;
  memberId: string;
  nickname: string;
  profilePath: string;
}

export interface UpdatePostRequestDto {
  id: number;
  content: string;
  imagePath: string;
}

export interface GetPostResponseDto {
  id: number;
  content: string;
  imagePath: string;
  totalLikeCount: number;
  isLike: boolean;
  memberId: number;
  nickname: string;
  profilePath: string;
  comments: {
    totalCount: number;
    comments: {
      id: number;
      content: string;
      memberId: number;
      nickname: string;
      profilePath: string;
    }[];
  };
}

export interface GetPostsParams {
  uuid?: string;
  page?: number;
}

export type GetPostsResponseDto = {
  totalCount: number;
  totalPages: number;
  currentPage: number;
  posts: {
    id: number;
    content: string;
    imagePath: string;
    totalLikeCount: number;
    isLike: boolean;
    memberId: number;
    nickname: string;
    profilePath: string;
    commentCount: number;
  }[];
};
