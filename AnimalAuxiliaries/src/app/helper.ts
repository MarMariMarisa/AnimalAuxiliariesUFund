import { Need } from "./need"

export interface Helper{
    id: number,
    username: string,
    basket: {
        needs: Need[]
    }
}