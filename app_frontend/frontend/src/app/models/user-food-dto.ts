import { FoodDTO } from "./food-dto"
import { UserDTO } from "./user-dto"

export class UserFoodDTO {
    id: number;
    user: UserDTO;
    food: FoodDTO;
    additionalColumn: string;
}