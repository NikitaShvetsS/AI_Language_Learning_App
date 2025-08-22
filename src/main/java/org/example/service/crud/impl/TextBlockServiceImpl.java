package org.example.service.crud.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.TextBlockRequestDTO;
import org.example.dto.response.TextBlockResponseDTO;
import org.example.exception.TextBlockNotFoundException;
import org.example.model.entity.TextBlock;
import org.example.repository.TextBlockRepository;
import org.example.service.crud.TextBlockService;
import org.example.mapper.TextBlockMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TextBlockServiceImpl implements TextBlockService {

    private final TextBlockRepository textBlockRepository;
    private final TextBlockMapper mapper;

    @Override
    public TextBlockResponseDTO createTextBlock(TextBlockRequestDTO dto) {
        TextBlock textBlock = textBlockRepository.save(mapper.toEntity(dto));
        return mapper.toDto(textBlock);
    }

    @Override
    public TextBlockResponseDTO updateTextBlock(UUID uuid, TextBlockRequestDTO dto) {

        TextBlock textBlock = textBlockRepository.findById(uuid)
                .orElseThrow(() -> new TextBlockNotFoundException("Text block not found"));

        textBlock.setContent(dto.getContent());
        textBlock.setLanguage(dto.getLanguage());

        textBlockRepository.save(textBlock);

        return mapper.toDto(textBlock);
    }

    @Override
    public TextBlockResponseDTO getTextBlockById(UUID uuid) {
        TextBlock textBlock = textBlockRepository.findById(uuid)
                .orElseThrow(() -> new TextBlockNotFoundException("Text block not found"));
        return mapper.toDto(textBlock);
    }

    @Override
    public List<TextBlockResponseDTO> getAllTextBlocks() {
        return textBlockRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public void deleteTextBlock(UUID uuid) {
        textBlockRepository.deleteById(uuid);
    }
}
