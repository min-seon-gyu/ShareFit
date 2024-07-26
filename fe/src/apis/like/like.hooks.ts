import { useMutation } from '@tanstack/react-query';
import { addLikeApi, cancelLikeApi } from './like.apis';

/** 좋아요 추가 hook */
export const useAddLike = () => {
  const { mutate: addLikeMutate, ...rest } = useMutation({
    mutationFn: (id: number) => addLikeApi(id),
  });

  return { addLikeMutate, ...rest };
};

/** 좋아요 취소 hook */
export const useCacncelLike = () => {
  const { mutate: cancelLikeMutate, ...rest } = useMutation({
    mutationFn: (id: number) => cancelLikeApi(id),
  });

  return { cancelLikeMutate, ...rest };
};
