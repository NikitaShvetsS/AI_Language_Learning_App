package org.example.mapper;

import org.example.dto.request.ExerciseRequestDTO;
import org.example.dto.response.ExerciseResponseDTO;
import org.example.model.entity.Exercise;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ExerciseMapper {

    public Exercise toEntity(ExerciseRequestDTO dto){

        return Exercise.builder()
                .uuid(UUID.randomUUID())
                .answer(dto.getAnswer())
                .build();
    }

    public ExerciseResponseDTO toDto(Exercise exercise){

        return ExerciseResponseDTO.builder()
                .uuid(exercise.getUuid())
                .type(exercise.getType())
                .maxScore(exercise.getMaxScore())
                .prompt(exercise.getPrompt())
                .build();

    }

}
