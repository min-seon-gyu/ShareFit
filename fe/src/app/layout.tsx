import type { Metadata } from 'next';
import { containerStyle } from './layout.css';
import './globals.css';

export const metadata: Metadata = {
  title: 'ShareFit',
  description: 'ShareFit Description',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <div className={containerStyle}>{children}</div>
      </body>
    </html>
  );
}
