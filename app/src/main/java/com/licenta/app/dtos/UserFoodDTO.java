package com.licenta.app.dtos;

import com.licenta.app.entities.Food;
import com.licenta.app.entities.User;

public class UserFoodDTO {

    private UserDTO user;
    private FoodDTO food;
    private String additionalColumn;

    public UserFoodDTO(UserDTO user, FoodDTO food, String additionalColumn) {
        this.user = user;
        this.food = food;
        this.additionalColumn = additionalColumn;
    }

    public UserFoodDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public FoodDTO getFood() {
        return food;
    }

    public void setFood(FoodDTO food) {
        this.food = food;
    }

    public String getAdditionalColumn() {
        return additionalColumn;
    }

    public void setAdditionalColumn(String additionalColumn) {
        this.additionalColumn = additionalColumn;
    }
}
