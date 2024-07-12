import axios from 'axios';

const API = axios.create({
  baseURL: 'https://13.209.118.165:8443',
  headers: {
    'Content-Type': 'application/json',
  },
});

export default API;
