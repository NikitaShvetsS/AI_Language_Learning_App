package org.example.service.crud.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.ExerciseRequestDTO;
import org.example.dto.response.ExerciseResponseDTO;
import org.example.exception.ExerciseNotFoundException;
import org.example.model.entity.Exercise;
import org.example.repository.ExerciseRepository;
import org.example.service.crud.ExerciseService;
import org.example.mapper.ExerciseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper mapper;
    @Override
    public ExerciseResponseDTO createExercise(ExerciseRequestDTO dto) {
        Exercise exercise = exerciseRepository.save(mapper.toEntity(dto));
        return mapper.toDto(exercise);
    }

    @Override
    public ExerciseResponseDTO updateExercise(UUID uuid, ExerciseRequestDTO dto) {

        Exercise exercise = exerciseRepository.findById(uuid)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise not found"));

        exercise.setAnswer(dto.getAnswer());
        exerciseRepository.save(exercise);

        return mapper.toDto(exercise);
    }

    @Override
    public ExerciseResponseDTO getExerciseById(UUID uuid) {
        Exercise exercise = exerciseRepository.findById(uuid)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise not found"));
        return mapper.toDto(exercise);
    }

    @Override
    public List<ExerciseResponseDTO> getAllExercises() {
        return exerciseRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public void deleteExercise(UUID uuid) {
        exerciseRepository.deleteById(uuid);
    }
}
