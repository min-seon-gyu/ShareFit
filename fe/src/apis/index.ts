import axios, { AxiosResponse, InternalAxiosRequestConfig } from 'axios';

const API = axios.create({
  baseURL: process.env.NEXT_PUBLIC_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

/** Request */
API.interceptors.request.use(
  function (config: InternalAxiosRequestConfig) {
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
