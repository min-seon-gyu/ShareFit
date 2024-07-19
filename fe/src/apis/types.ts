export interface ApiResponse<T = any> {
  data: T;
  message: string;
  path: string;
  statusCode: number;
  timestamp: string;
}
