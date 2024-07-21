import {
  createPostApi,
  deletePostApi,
  getPostApi,
  updatePostApi,
  uploadImageApi,
} from './post.apis';
import { postQueryKeys } from './post.queryKeys';
import { CreatePostRequestDto, UpdatePostRequestDto, UploadImageRequestDto } from './post.types';
import { useMutation, useQuery } from '@tanstack/react-query';

/** 이미지 업로드 hook */
export const useUploadImage = () => {
  const { mutate: uploadImageMutate, ...rest } = useMutation({
    mutationFn: ({ formData }: UploadImageRequestDto) => uploadImageApi({ formData }),
  });

  return { uploadImageMutate, ...rest };
};

/** 게시글 등록 hook */
export const useCreatePost = () => {
  const { mutate: createPostMutate, ...rest } = useMutation({
    mutationFn: ({ content, imagePath }: CreatePostRequestDto) =>
      createPostApi({ content, imagePath }),
  });

  return { createPostMutate, ...rest };
};

/** 게시글 업데이트 hook */
export const useUpdatePost = () => {
  const { mutate: updatePostMutate, ...rest } = useMutation({
    mutationFn: ({ id, content, imagePath }: UpdatePostRequestDto) =>
      updatePostApi({ id, content, imagePath }),
  });

  return { updatePostMutate, ...rest };
};

/** 게시글 조회 hook */
export const useGetPost = (id: number) => {
  const { data: postData, ...rest } = useQuery({
    queryKey: postQueryKeys.detail(id),
    queryFn: async () => {
      return (await getPostApi(id)).data.data;
    },
  });

  return { postData, ...rest };
};

/** 게시글 삭제 hook */
export const useDeletePost = () => {
  const { mutate: deletePostMutate, ...rest } = useMutation({
    mutationFn: (id: number) => deletePostApi(id),
  });

  return { deletePostMutate, ...rest };
};
