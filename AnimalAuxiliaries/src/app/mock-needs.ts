import { Need } from './need';

export const NEEDS: Need[] = [
  {
    id: '1',
    name: 'Dog-Leash',
    description: 'The dogs we have are going to need leashes',
    type: 'Equipment',
    price: 15,
    quantity: 5,
    numInBaskets: 0,
    quantityFunded: 0,
  },
  {
    id: '2',
    name: 'Dog-Food',
    description: 'The dogs are going to need food',
    type: 'Food',
    price: 20,
    quantity: 5,
    numInBaskets: 0,
    quantityFunded: 0,
  },
  {
    id: '3',
    name: 'Cat-Toys',
    description: 'The cats need toys to play with',
    type: 'Equipment',
    price: 7,
    quantity: 5,
    numInBaskets: 0,
    quantityFunded: 0,
  },
];
