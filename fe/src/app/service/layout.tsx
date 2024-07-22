import React from 'react';
import { pageStyle } from './layout.css';
import Header from '@/components/layouts/header';

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <div className={pageStyle}>
      <Header />

      {children}
    </div>
  );
}
