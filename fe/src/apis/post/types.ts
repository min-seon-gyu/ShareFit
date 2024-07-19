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
