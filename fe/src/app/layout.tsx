import type { Metadata } from 'next';
import { containerStyle } from './layout.css';
import './globals.css';
import TanstackQueryProvider from '@/providers/TanstackQueryProvider';

export const metadata: Metadata = {
  title: 'ShareFit',
  description: '오늘의 스타일을 세상과 공유하다',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <TanstackQueryProvider>
          <div className={containerStyle}>{children}</div>
        </TanstackQueryProvider>
      </body>
    </html>
  );
}
