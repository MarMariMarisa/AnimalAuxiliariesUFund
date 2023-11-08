import { Need } from './need';

export interface Helper {
  id: number;
  password: string;
  username: string;
  basket: {
    needs: Need[];
  };
}
