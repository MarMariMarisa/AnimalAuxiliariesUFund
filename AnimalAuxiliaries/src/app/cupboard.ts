import { Need } from "./need";

export interface Cupboard{
    currentNeeds: Need[],
    retiredNeeds: Need[]
}
