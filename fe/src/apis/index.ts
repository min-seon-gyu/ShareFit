import axios from 'axios';

const API = axios.create({
  baseURL: 'https://sharefit-server.store',
  headers: {
    'Content-Type': 'application/json',
  },
});

export default API;
