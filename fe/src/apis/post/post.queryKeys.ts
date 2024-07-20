export const postQueryKeys = {
  all: ['posts'] as const,
  detail: (id: number) => [...postQueryKeys.all, id] as const,
};
