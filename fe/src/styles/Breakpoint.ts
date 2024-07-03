export const breakpoints: { [index: string]: number } = {
  xs: 576,
  sm: 768,
  md: 992,
  lg: 1400,
} as const;

// Media Query
export const mq = Object.keys(breakpoints)
  .map((key) => [key, breakpoints[key]] as [string, number])
  .reduce(
    (prev, [key, breakpoint]) => {
      prev[key] = `screen and (max-width: ${breakpoint}px)`;
      return prev;
    },
    {} as { [index: string]: string },
  );
