import API from '..';

/** 좋아요 추가 api */
export const addLikeApi = async (id: number) => {
  return API.post(`/like/${id}`);
};

/** 좋아요 취소 api */
export const cancelLikeApi = async (id: number) => {
  return API.delete(`/like/${id}`);
};
