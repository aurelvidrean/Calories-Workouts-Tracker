import { UserFoodDTO } from "./user-food-dto";

export class UserDTO {
    id: number;
    name: string;
    password: string;
    age: number;
    calories: number;
    kilograms: number;
    foods: Set<UserFoodDTO>;
    exercises: Set<number>;
}