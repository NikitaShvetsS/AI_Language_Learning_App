package org.example.service.crud;

import org.example.dto.request.TextBlockRequestDTO;
import org.example.dto.response.TextBlockResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TextBlockService {

    TextBlockResponseDTO createTextBlock(TextBlockRequestDTO dto);
    TextBlockResponseDTO updateTextBlock(UUID uuid, TextBlockRequestDTO dto);
    TextBlockResponseDTO getTextBlockById(UUID uuid);
    List<TextBlockResponseDTO> getAllTextBlocks();
    void deleteTextBlock(UUID uuid);

}
