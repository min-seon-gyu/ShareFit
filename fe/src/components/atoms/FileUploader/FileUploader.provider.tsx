import { createContext, PropsWithChildren } from 'react';

export const FileUploaderContext = createContext<{ handleFile: (file: File) => void } | null>(null);

interface Props extends PropsWithChildren {
  handleFile: (file: File) => void;
}

export const FileUploaderProvider = ({ children, handleFile }: Props) => {
  return (
    <FileUploaderContext.Provider value={{ handleFile }}>{children}</FileUploaderContext.Provider>
  );
};
