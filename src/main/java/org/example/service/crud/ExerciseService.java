package org.example.service.crud;

import org.example.dto.request.ExerciseRequestDTO;
import org.example.dto.response.ExerciseResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ExerciseService {

    ExerciseResponseDTO createExercise(ExerciseRequestDTO dto);
    ExerciseResponseDTO updateExercise(UUID uuid, ExerciseRequestDTO dto);
    ExerciseResponseDTO getExerciseById(UUID uuid);
    List<ExerciseResponseDTO> getAllExercises();
    void deleteExercise(UUID uuid);

}
