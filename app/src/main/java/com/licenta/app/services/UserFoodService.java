package com.licenta.app.services;

import com.licenta.app.dtos.UserFoodDTO;
import com.licenta.app.entities.UserFood;
import com.licenta.app.repositories.UserFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserFoodService {

    private final UserFoodRepository userFoodRepository;

    @Autowired
    public UserFoodService(UserFoodRepository userFoodRepository) {
        this.userFoodRepository = userFoodRepository;
    }

    public UserFood getUserById(Long userId) {
        Optional<UserFood> optionalUser = userFoodRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new RuntimeException("User not found with ID: " + userId);
    }

    public List<UserFoodDTO> getAllUserFoods() {
        List<UserFood> userFoods = userFoodRepository.findAll();

        return userFoods.stream().map(UserFoodService::fromEntityToDTO).collect(Collectors.toList());
    }

    public static UserFoodDTO fromEntityToDTO(UserFood userFood) {
        UserFoodDTO userFoodDTO = new UserFoodDTO(UserService.buildUserDTOFromEntity(userFood.getUser()), FoodService.buildFoodDTOFromEntity(userFood.getFood()), userFood.getAdditionalColumn());
        return userFoodDTO;
    }

}
