import { Principal } from './Principal';

export interface User {
  username: string;
  password: string;
  role: 'ROLE_USER';
  principal: Principal;
}
