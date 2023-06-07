package com.licenta.app.controllers;

import com.licenta.app.dtos.ExerciseDTO;
import com.licenta.app.dtos.FoodDTO;
import com.licenta.app.entities.Exercise;
import com.licenta.app.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ExerciseDTO createExercise(@RequestBody ExerciseDTO exerciseDTO) {
        return exerciseService.createExercise(exerciseDTO);
    }

    @GetMapping("/{exerciseId}")
    public ExerciseDTO getExerciseById(@PathVariable Long exerciseId) {
        return exerciseService.getExerciseById(exerciseId);
    }

    @GetMapping()
    public List<ExerciseDTO> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/find/{exerciseName}")
    public ExerciseDTO getExerciseByName(@PathVariable String exerciseName) {
        return exerciseService.findByName(exerciseName);
    }
}
