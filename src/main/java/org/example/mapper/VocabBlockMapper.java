package org.example.mapper;

import org.example.dto.request.VocabBlockRequestDTO;
import org.example.dto.response.VocabBlockResponseDTO;
import org.example.model.entity.VocabBlock;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VocabBlockMapper {
    public VocabBlock toEntity(VocabBlockRequestDTO dto) {

        return VocabBlock.builder()
                .uuid(UUID.randomUUID())
                .word(dto.getWord())
                .speechPart(dto.getSpeechPart())
                .translation(dto.getTranslation())
                .build();
    }

    public VocabBlockResponseDTO toDto(VocabBlock vocabBlock) {

        return VocabBlockResponseDTO.builder()
                .uuid(vocabBlock.getUuid())
                .word(vocabBlock.getWord())
                .translation(vocabBlock.getTranslation())
                .speechPart(vocabBlock.getSpeechPart())
                .timesSeen(vocabBlock.getTimesSeen())
                .isKnown(vocabBlock.isKnown())
                .addedAt(vocabBlock.getAddedAt())
                .build();

    }
}
