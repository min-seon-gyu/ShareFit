import { RefObject, useEffect, useState } from 'react';

interface InfiniteScrollParams {
  ref: RefObject<HTMLElement>;
  callback: () => void;
}

const options = {
  threshold: 0,
};

export const useInfiniteScroll = ({ ref, callback }: InfiniteScrollParams) => {
  useEffect(() => {
    if (typeof IntersectionObserver === undefined) return;

    const handleObserver = (entries: IntersectionObserverEntry[]) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          callback();
        }
      });
    };

    const observer = new IntersectionObserver(handleObserver, options);

    if (ref.current) {
      observer.observe(ref.current);
    }

    return () => observer.disconnect();
  }, [ref, callback]);
};
