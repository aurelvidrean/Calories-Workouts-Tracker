import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ExerciseDTO } from "../models/exercise-dto";

@Injectable({
    providedIn:'root'
})
export class ExerciseService {
    private apiUrl = 'http://localhost:8080/exercises';

    constructor(private http: HttpClient) {}

  getAllExercises(): Observable<ExerciseDTO[]> {
    return this.http.get<ExerciseDTO[]>(this.apiUrl);
  }

  getExerciseByName(name: string): Observable<ExerciseDTO> {
    const url = `${this.apiUrl}/find/${name}`;
    return this.http.get<ExerciseDTO>(url);
  }
}