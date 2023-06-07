import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { FoodDTO } from "../models/food-dto";

@Injectable({
    providedIn: 'root'
})
export class FoodService {
    private apiUrl = 'http://localhost:8080/foods';

    constructor(private http: HttpClient) {}

    private foodId: number | null = null;

    setFoodId(foodId: number): void {
        this.foodId = foodId;
    }

    getFoodId(): number | null {
        return this.foodId;
    }


  getAllFoods(): Observable<FoodDTO[]> {
    return this.http.get<FoodDTO[]>(this.apiUrl);
  }

  getFoodByName(name: string): Observable<FoodDTO> {
    const url = `${this.apiUrl}/find/${name}`;
    return this.http.get<FoodDTO>(url);
  }

}