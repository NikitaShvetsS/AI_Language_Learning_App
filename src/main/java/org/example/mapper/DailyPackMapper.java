package org.example.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.DailyPackRequestDTO;
import org.example.dto.response.DailyPackResponseDTO;
import org.example.dto.response.TextBlockResponseDTO;
import org.example.dto.response.ExerciseResponseDTO;
import org.example.dto.response.VocabBlockResponseDTO;
import org.example.exception.UserNotFoundException;
import org.example.model.entity.DailyPack;
import org.example.model.entity.User;
import org.example.repository.VocabBlockRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DailyPackMapper {
    private final UserRepository userRepository;
    private final TextBlockMapper textBlockMapper;
    private final ExerciseMapper exerciseMapper;
    private final VocabBlockRepository vocabBlockRepository;

    public DailyPack toEntity(DailyPackRequestDTO dto){
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found: " + dto.getUserId()));

        return DailyPack.builder()
                .user(user)
                .packDate(dto.getPackDate())
                .status(dto.getStatus())
                .build();
    }

    public DailyPackResponseDTO toDto(DailyPack entity){

        List<TextBlockResponseDTO> texts = Optional.ofNullable(entity.getTextBlock())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(textBlockMapper::toDto)
                .toList();

        List<ExerciseResponseDTO> exercises = Optional.ofNullable(entity.getExercises())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(exerciseMapper::toDto)
                .toList();

        return DailyPackResponseDTO.builder()
                .uuid(entity.getUuid())
                .createdAt(entity.getCreatedAt())
                .textBlocks(texts)
                .modelUsed(entity.getModelUsed())
                .packDate(entity.getPackDate())
                .status(entity.getStatus())
                .exercises(exercises)
                .vocabBlock(vocabBlockRepository.findBySourcePack(entity).stream()
                        .map(v -> VocabBlockResponseDTO.builder()
                                .uuid(v.getUuid())
                                .word(v.getWord())
                                .translation(v.getTranslation())
                                .speechPart(v.getSpeechPart())
                                .timesSeen(v.getTimesSeen())
                                .isKnown(v.isKnown())
                                .addedAt(v.getAddedAt())
                                .build())
                        .toList())
                .build();

    }

}
