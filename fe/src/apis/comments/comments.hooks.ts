import { useMutation } from '@tanstack/react-query';
import { CreateCommentRequestDto } from './comments.type';
import { createCommentApi, deleteCommentApi } from './comments.apis';

/** 댓글 추가 hook */
export const useCreateComment = () => {
  const { mutate: createCommentMutate, ...rest } = useMutation({
    mutationFn: ({ content, id }: CreateCommentRequestDto) => createCommentApi({ content, id }),
  });

  return { createCommentMutate, ...rest };
};

/** 댓글 삭제 hook */
export const useDeleteComment = () => {
  const { mutate: deleteCommentMutate, ...rest } = useMutation({
    mutationFn: (id: number) => deleteCommentApi(id),
  });

  return { deleteCommentMutate, ...rest };
};
