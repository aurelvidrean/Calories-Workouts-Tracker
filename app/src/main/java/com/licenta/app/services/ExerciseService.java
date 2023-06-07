package com.licenta.app.services;

import com.licenta.app.dtos.ExerciseDTO;
import com.licenta.app.dtos.FoodDTO;
import com.licenta.app.entities.Exercise;
import com.licenta.app.entities.Food;
import com.licenta.app.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExerciseService {

    private static ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public ExerciseDTO createExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = buildExerciseEntityFromDTO(exerciseDTO);
        Exercise savedExercise = exerciseRepository.save(exercise);
        return buildExerciseDTOFromEntity(savedExercise);
    }

    public ExerciseDTO updateExercise(Long exerciseId, ExerciseDTO exerciseDTO) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(exerciseId);
        if (optionalExercise.isPresent()) {
            Exercise exercise = optionalExercise.get();
            updateFieldsFromDTO(exercise, exerciseDTO);
            Exercise updatedExercise = exerciseRepository.save(exercise);
            return buildExerciseDTOFromEntity(updatedExercise);
        }
        throw new RuntimeException("Exercise not found with ID: " + exerciseId);
    }

    public void deleteExercise(Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }

    public ExerciseDTO getExerciseById(Long exerciseId) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(exerciseId);
        if (optionalExercise.isPresent()) {
            Exercise exercise = optionalExercise.get();
            return buildExerciseDTOFromEntity(exercise);
        }
        throw new RuntimeException("Exercise not found with ID: " + exerciseId);
    }

    public static Exercise buildExerciseEntityFromDTO(ExerciseDTO exerciseDTO) {
        Exercise exercise = new Exercise();
        exercise.setName(exerciseDTO.getName());
        exercise.setImageUrl(exerciseDTO.getImageUrl());
        exercise.setCalories(exerciseDTO.getCalories());
        return exercise;
    }

    public static ExerciseDTO buildExerciseDTOFromEntity(Exercise exercise) {
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setId(exercise.getId());
        exerciseDTO.setName(exercise.getName());
        exerciseDTO.setCalories(exercise.getCalories());
        exerciseDTO.setImageUrl(exercise.getImageUrl());
        return exerciseDTO;
    }

    private void updateFieldsFromDTO(Exercise exercise, ExerciseDTO exerciseDTO) {
        exercise.setName(exerciseDTO.getName());
        exercise.setImageUrl(exerciseDTO.getImageUrl());
        exercise.setCalories(exerciseDTO.getCalories());
    }

    public List<ExerciseDTO> getAllExercises() {
        List<Exercise> allExerciseList = exerciseRepository.findAll();
        return allExerciseList.stream().map(ExerciseService::buildExerciseDTOFromEntity).collect(Collectors.toList());
    }

    public static Exercise getById(Long exerciseId) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        if (exerciseOptional.isPresent()) {
            return exerciseOptional.get();
        }
        throw new RuntimeException("Exercise not found with ID: " + exerciseId);
    }

    public ExerciseDTO findByName(String name) {
        List<Exercise> exercises = exerciseRepository.findAll();
        Optional<Exercise> exerciseOptional = exercises.stream().filter(exercise -> Objects.equals(exercise.getName(), name)).findFirst();
        if (!exerciseOptional.isPresent()) {
            throw new RuntimeException("Invalid name");
        }
        ExerciseDTO exerciseDTO = buildExerciseDTOFromEntity(exerciseOptional.get());

        return exerciseDTO;
    }
}
