package com.licenta.app.services;

import com.licenta.app.dtos.ExerciseDTO;
import com.licenta.app.dtos.FoodDTO;
import com.licenta.app.dtos.UserDTO;
import com.licenta.app.entities.Exercise;
import com.licenta.app.entities.Food;
import com.licenta.app.entities.User;
import com.licenta.app.entities.UserFood;
import com.licenta.app.enums.MomentOfTheDay;
import com.licenta.app.repositories.ExerciseRepository;
import com.licenta.app.repositories.FoodRepository;
import com.licenta.app.repositories.UserFoodRepository;
import com.licenta.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final FoodRepository foodRepository;

    private final ExerciseRepository exerciseRepository;

    private final UserFoodRepository userFoodRepository;
    private final FoodService foodService;
    private final ExerciseService exerciseService;

    @Autowired
    public UserService(UserRepository userRepository, FoodService foodService, ExerciseService exerciseService, FoodRepository foodRepository, ExerciseRepository exerciseRepository, UserFoodRepository userFoodRepository) {
        this.userRepository = userRepository;
        this.foodService = foodService;
        this.exerciseService = exerciseService;
        this.foodRepository = foodRepository;
        this.exerciseRepository = exerciseRepository;
        this.userFoodRepository = userFoodRepository;
    }
    public UserDTO createUser(UserDTO userDTO) {
        User user = buildUserEntityFromDTO(userDTO);
        User savedUser = userRepository.save(user);
        return buildUserDTOFromEntity(savedUser);
    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            updateFieldsFromDTO(user, userDTO);
            User updatedUser = userRepository.save(user);
            return buildUserDTOFromEntity(updatedUser);
        }
        throw new RuntimeException("User not found with ID: " + userId);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(UserService::buildUserDTOFromEntity).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return buildUserDTOFromEntity(user);
        }
        throw new RuntimeException("User not found with ID: " + userId);
    }

    public void addFoodToUser(Long userId, Long foodId, MomentOfTheDay momentOfTheDay) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Food> optionalFood = foodRepository.findById(foodId);
            if (optionalFood.isPresent()) {
                Food food = optionalFood.get();

                UserFood userFood = new UserFood(user, food);
                userFood.setAdditionalColumn(momentOfTheDay.toString());

                user.getFoods().add(userFood);

                userFoodRepository.save(userFood);
                int updatedCalories = user.getCalories() + food.getCalories();
                user.setCalories(updatedCalories);

                userRepository.save(user);
            } else {
                throw new RuntimeException("Food not found with ID: " + foodId);
            }
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    public void addExerciseToUser(Long userId, Long exerciseId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Exercise> optionalExercise = exerciseRepository.findById(exerciseId);
            if (optionalExercise.isPresent()) {
                Exercise exercise = optionalExercise.get();
                Set<Exercise> exerciseList = user.getExercises();
                exerciseList.add(exercise);
                user.setExercises(exerciseList);
                int updatedCalories = user.getCalories() - exercise.getCalories();
                user.setCalories(updatedCalories);
            } else {
                throw new RuntimeException("Exercise not found with ID: " + exerciseId);
            }
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    public static User buildUserEntityFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setCalories(userDTO.getCalories());
        user.setKilograms(userDTO.getKilograms());
        user.setAge(userDTO.getAge());
        user.setPassword(userDTO.getPassword());
        if (userDTO.getFoods() != null) {
            List<Food> foodSet = userDTO.getFoods().stream().map(FoodService::getById).collect(Collectors.toList());
            foodSet.stream().forEach( food -> {
                UserFood u = new UserFood(user, food);
                user.getFoods().add(u);
            });
        }
        if (userDTO.getExerciseIds() != null) {
            Set<Exercise> exerciseSet = userDTO.getExerciseIds().stream().map(ExerciseService::getById).collect(Collectors.toSet());
            user.setExercises(exerciseSet);
        }
        return user;
    }

    public static UserDTO buildUserDTOFromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setAge(user.getAge());
        userDTO.setCalories(user.getCalories());
        userDTO.setKilograms(user.getKilograms());
        Set<Long> exercisesIds = user.getExercises().stream().map(Exercise::getId).collect(Collectors.toSet());
        userDTO.setExerciseIds(exercisesIds);
        //Set<Long> foodIds = user.getFoods().stream().map(Food::getId).collect(Collectors.toSet());
        List<Long> fid = new ArrayList<>();
        user.getFoods().stream().forEach(userFood -> {
            fid.add(userFood.getFood().getId());
        });
        userDTO.setFoods(fid);
        return userDTO;
    }

    private void updateFieldsFromDTO(User user, UserDTO userDTO) {
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setCalories(userDTO.getCalories());
        user.setKilograms(userDTO.getKilograms());
        Set<Food> foodSet = userDTO.getFoods().stream().map(FoodService::getById).collect(Collectors.toSet());
        foodSet.stream().forEach(food -> {
            UserFood u = new UserFood(user, food);
            user.getFoods().add(u);

        });
        Set<Exercise> exerciseSet = userDTO.getExerciseIds().stream().map(ExerciseService::getById).collect(Collectors.toSet());
        user.setExercises(exerciseSet);
    }

    public UserDTO login(String username, String password) {
        User user = userRepository.findByNameAndPassword(username, password);
        if (user == null) {
            throw new RuntimeException("Invalid username or password");
        }
        // Map the User entity to UserDTO
        UserDTO userDTO = buildUserDTOFromEntity(user);

        return userDTO;
    }

    public List<FoodDTO> getUserFoods(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<FoodDTO> foodDTOS = new ArrayList<>();
            for (UserFood userFood : user.get().getFoods()) {
                Food f = userFood.getFood();
                FoodDTO foodDTO = FoodService.buildFoodDTOFromEntity(f);
                foodDTOS.add(foodDTO);
            }
            return foodDTOS;
        }
        throw new RuntimeException("Invalid userId");
    }

    public List<ExerciseDTO> getUserExercises(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getExercises().stream().map(ExerciseService::buildExerciseDTOFromEntity).collect(Collectors.toList());
        }
        throw new RuntimeException("Invalid userId");
    }
}