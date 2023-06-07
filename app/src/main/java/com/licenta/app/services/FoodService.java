package com.licenta.app.services;


import com.licenta.app.dtos.FoodDTO;
import com.licenta.app.dtos.UserDTO;
import com.licenta.app.entities.Food;
import com.licenta.app.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FoodService {

    private static FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public static Food buildFoodEntityFromDTO(FoodDTO foodDTO) {
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setImageUrl(foodDTO.getImageUrl());
        food.setLoseWeightType(foodDTO.isLoseWeightType());
        food.setCalories(foodDTO.getCalories());
        return food;
    }

    public static FoodDTO buildFoodDTOFromEntity(Food food) {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(food.getId());
        foodDTO.setName(food.getName());
        foodDTO.setImageUrl(food.getImageUrl());
        foodDTO.setLoseWeightType(food.isLoseWeightType());
        foodDTO.setCalories(food.getCalories());
        return foodDTO;
    }

    private void updateFieldsFromDTO(Food food, FoodDTO foodDTO) {
        food.setName(foodDTO.getName());
        food.setImageUrl(foodDTO.getImageUrl());
        food.setLoseWeightType(foodDTO.isLoseWeightType());
        food.setCalories(foodDTO.getCalories());
    }

    public FoodDTO createFood(FoodDTO foodDTO) {
        Food food = buildFoodEntityFromDTO(foodDTO);
        Food savedFood = foodRepository.save(food);
        return buildFoodDTOFromEntity(savedFood);
    }

    public List<FoodDTO> getAllFoods() {
        List<Food> allFoodList = foodRepository.findAll();
        return allFoodList.stream().map(FoodService::buildFoodDTOFromEntity).collect(Collectors.toList());
    }

    public FoodDTO updateFood(Long foodId, FoodDTO foodDTO) {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if (optionalFood.isPresent()) {
            Food food = optionalFood.get();
            updateFieldsFromDTO(food, foodDTO);
            Food updatedFood = foodRepository.save(food);
            return buildFoodDTOFromEntity(updatedFood);
        }
        throw new RuntimeException("Food not found with ID: " + foodId);
    }

    public void deleteFood(Long foodId) {
        foodRepository.deleteById(foodId);
    }

    public FoodDTO getFoodById(Long foodId) {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if (optionalFood.isPresent()) {
            Food food = optionalFood.get();
            return buildFoodDTOFromEntity(food);
        }
        throw new RuntimeException("Food not found with ID: " + foodId);
    }

    public static Food getById(Long foodId) {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if (optionalFood.isPresent()) {
            return  optionalFood.get();
        }
        throw new RuntimeException("Food not found with ID: " + foodId);
    }

    public FoodDTO findByName(String name) {
        List<Food> food = foodRepository.findAll();
        Optional<Food> f = food.stream().filter(food1 -> Objects.equals(food1.getName(), name)).findFirst();
        if (!f.isPresent()) {
            throw new RuntimeException("Invalid name");
        }
        FoodDTO foodDTO = buildFoodDTOFromEntity(f.get());

        return foodDTO;
    }
}
