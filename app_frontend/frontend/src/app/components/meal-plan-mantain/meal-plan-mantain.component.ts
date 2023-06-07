import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ExerciseDTO } from 'src/app/models/exercise-dto';
import { FoodDTO } from 'src/app/models/food-dto';
import { ExerciseService } from 'src/app/services/exercise-service';
import { FoodService } from 'src/app/services/food-service';
import { UserService } from 'src/app/services/user-service';

@Component({
  selector: 'app-meal-plan-mantain',
  templateUrl: './meal-plan-mantain.component.html',
  styleUrls: ['./meal-plan-mantain.component.css']
})
export class MealPlanMantainComponent implements OnInit{
  foods: FoodDTO[] = [];
  exercises: ExerciseDTO[] = [];
  selectedBreakfast: string = '';
  selectedLunch: string = '';
  selectedDinner: string = '';
  selectedExercise: string = '';

  totalCalories: number = 0;

  breakfastTotalCalories: number = 0;
  lunchTotalCalories: number = 0;
  dinnerTotalCalories: number = 0;
  exerciseTotalCalories: number = 0;

  foodSelectedForBreakfast: FoodDTO[] = [];
  foodSelectedForLunch: FoodDTO[] = [];
  foodSelectedForDinner: FoodDTO[] = [];

  exercisesSelected: ExerciseDTO[] = [];

  constructor(private foodService: FoodService, private exerciseService: ExerciseService, private userService: UserService, private titlseService: Title) {}

  ngOnInit(): void {
    this.titlseService.setTitle('Here is your plan to mantain weight')
    this.getFoods();
    this.getExercises();
    const userIdString = localStorage.getItem('userId'); 
    const userIdBun = parseInt(userIdString); 
    console.log(userIdBun)
    this.userService.getUserExercises(userIdBun).forEach( exercise => {
      this.exercisesSelected = exercise
    });

    this.userService.getAllUserFoods().forEach( userFood => {
      console.log(userFood);
      userFood.forEach( uF => {
        if (uF.additionalColumn === "breakfast" && uF.user.id === userIdBun) {
          console.log("mai departe");
          console.log(uF.food);
          this.foodSelectedForBreakfast.push(uF.food);
        }
        if (uF.additionalColumn === 'lunch' && uF.user.id === userIdBun) {
          this.foodSelectedForLunch.push(uF.food);
        }
        if (uF.additionalColumn === 'dinner' && uF.user.id === userIdBun) {
          this.foodSelectedForDinner.push(uF.food);
        }
      })
    });
  }

  getFoods(): void {
    this.foodService.getAllFoods().subscribe(foods => {
      this.foods = foods;
    });
  }

  getExercises(): void {
    this.exerciseService.getAllExercises().subscribe(exercises => {
      this.exercises = exercises;
    });
  }

  addFoodToMeal(mealType: string): void {
    if (mealType === 'breakfast') {
      this.foodService.getFoodByName(this.selectedBreakfast).subscribe( food => {
        this.foodSelectedForBreakfast.push(food);
        const userId = this.userService.getUserId();
        console.log(userId)
        console.log(food.id)
        const userIdString = localStorage.getItem('userId'); 
        const userIdBun = parseInt(userIdString); 
        console.log(userIdBun)
        this.userService.addFoodToUser(userIdBun, food.id, mealType)

        this.calculateTotalCalories();
      
        
      });
    }
    if (mealType === 'lunch') {
      this.foodService.getFoodByName(this.selectedLunch).subscribe( food => {
        this.foodSelectedForLunch.push(food);
        const userIdString = localStorage.getItem('userId'); 
        const userIdBun = parseInt(userIdString); 
        this.userService.addFoodToUser(userIdBun, food.id, mealType)

        this.calculateTotalCalories();
      });
    }
    if (mealType === 'dinner') {
      this.foodService.getFoodByName(this.selectedDinner).subscribe( food => {
        this.foodSelectedForDinner.push(food);
        const userIdString = localStorage.getItem('userId'); 
        const userIdBun = parseInt(userIdString); 
        this.userService.addFoodToUser(userIdBun, food.id, mealType)
        
        this.calculateTotalCalories();
      });
    }
  }

  addExercise(): void {
    const userIdString = localStorage.getItem('userId'); 
    const userIdBun = parseInt(userIdString); 
    this.exerciseService.getExerciseByName(this.selectedExercise).subscribe( exercise => {
      console.log(exercise.name);
      this.exercisesSelected.push(exercise);
      
      this.userService.addExerciseToUser(userIdBun, exercise.id)
    })
  }

  calculateTotalCalories(): void {

    const userIdString = localStorage.getItem('userId'); 
    const userIdBun = parseInt(userIdString); 
    const user =  this.userService.findById(userIdBun)
    user.subscribe(user => {
        this.totalCalories = user.calories
    })
  }
}
