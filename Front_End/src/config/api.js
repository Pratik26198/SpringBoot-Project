import axios from 'axios';

const DEPLOYED_URL = 'https://elegantspringbootbackend-production.up.railway.app'; // Update this with actual Railway URL
const LOCALHOST = 'http://localhost:5454'; // Keep this for local testing

export const API_BASE_URL = process.env.NODE_ENV === 'production' ? DEPLOYED_URL : LOCALHOST;

const api = axios.create({
  baseURL: API_BASE_URL,
});

// Include JWT if available
const token = localStorage.getItem('jwt');
if (token) {
  api.defaults.headers.common['Authorization'] = `Bearer ${token}`;
}

api.defaults.headers.post['Content-Type'] = 'application/json';

export default api;
