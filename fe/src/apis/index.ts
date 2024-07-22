import axios, { AxiosResponse, InternalAxiosRequestConfig } from 'axios';
import { getCookie } from 'cookies-next';

const API = axios.create({
  baseURL: process.env.NEXT_PUBLIC_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

/** Request */
API.interceptors.request.use(
  function (config: InternalAxiosRequestConfig) {
    // 카카오 api는 별도의 헤더 전송 필요
    if (config.url?.includes('kakao.com')) {
      return config;
    }

    const accessToken = getCookie('accessToken');

    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }

    return config;
  },

  function (error) {
    return Promise.reject(error);
  },
);

/** Response */
API.interceptors.response.use(
  async function (response: AxiosResponse) {
    return response;
  },

  async function (error) {
    return Promise.reject(error.response.data);
  },
);

export default API;
