package org.example.mapper;

import org.example.dto.request.TextBlockRequestDTO;
import org.example.dto.response.TextBlockResponseDTO;
import org.example.model.entity.TextBlock;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TextBlockMapper {

    public TextBlock toEntity(TextBlockRequestDTO dto){

        return TextBlock.builder()
                .uuid(UUID.randomUUID())
                .content(dto.getContent())
                .language(dto.getLanguage())
                .build();

    }

    public TextBlockResponseDTO toDto(TextBlock textBlock) {

        return TextBlockResponseDTO.builder()
                .content(textBlock.getContent())
                .language(textBlock.getLanguage())
                .uuid(textBlock.getUuid())
                .build();

    }
}
