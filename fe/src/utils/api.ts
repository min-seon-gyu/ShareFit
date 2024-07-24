/**
 *  parse query Parameters to remove empty values
 *  ex) {name: 'hoon', age: ''} => {name: 'hoon'}
 */
export const queryFilter = (params: { [key: string]: any }) => {
  return Object.fromEntries(Object.entries(params).filter(([_, value]) => value !== ''));
};
