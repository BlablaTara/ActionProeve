import axios, { type AxiosInstance } from 'axios'

const axiosInstance: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 1000
})

export default axiosInstance
