'use client';

import { useRouter } from 'next/navigation';
import { useEffect, useState } from 'react';

export function RefreshHandler() {
  const { refresh } = useRouter();

  useEffect(() => {
    refresh();
  }, [refresh]);

  return null;
}
