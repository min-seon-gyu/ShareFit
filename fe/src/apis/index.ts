import axios from 'axios';

const API = axios.create({
  baseURL: 'http://13.209.118.165:8080',
  headers: {
    'Content-Type': 'application/json',
  },
});

export default API;
