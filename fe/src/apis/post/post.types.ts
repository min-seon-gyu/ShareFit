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

export interface CreatePostResponseDto {}

export interface UpdatePostRequestDto {
  id: number;
  content: string;
  imagePath: string;
}

export interface GetPostResponseDto {
  id: number;
  content: string;
  imagePath: string;
}
