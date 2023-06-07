package com.licenta.app.controllers;

import com.licenta.app.dtos.FoodDTO;
import com.licenta.app.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public FoodDTO createFood(@RequestBody FoodDTO foodDTO) {
        return foodService.createFood(foodDTO);
    }

    @GetMapping("/{foodId}")
    public FoodDTO getFoodById(@PathVariable Long foodId) {
        return foodService.getFoodById(foodId);
    }

    @GetMapping()
    public List<FoodDTO> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/find/{foodName}")
    public FoodDTO getFoodByName(@PathVariable String foodName) {
        return foodService.findByName(foodName);
    }
}