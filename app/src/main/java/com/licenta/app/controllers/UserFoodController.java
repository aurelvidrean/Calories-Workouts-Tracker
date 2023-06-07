package com.licenta.app.controllers;

import com.licenta.app.dtos.UserFoodDTO;
import com.licenta.app.entities.UserFood;
import com.licenta.app.services.UserFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/userfoods")
public class UserFoodController {

    private final UserFoodService userFoodService;

    @Autowired
    public UserFoodController(UserFoodService userFoodService) {
        this.userFoodService = userFoodService;
    }

    @GetMapping("/{userId}")
    public UserFood getUserFoodById(@PathVariable Long userId) {
        return userFoodService.getUserById(userId);
    }

    @GetMapping
    public List<UserFoodDTO> getAllUserFoods() {
        List<UserFoodDTO> userFoods = userFoodService.getAllUserFoods();
        return  userFoods;
       // return userFoodService.getAllUserFoods();
    }
}
