package org.example.service.crud;

import org.example.dto.request.VocabBlockRequestDTO;
import org.example.dto.response.VocabBlockResponseDTO;

import java.util.List;
import java.util.UUID;

public interface VocabService {

    VocabBlockResponseDTO createVocabBlock(VocabBlockRequestDTO dto);
    VocabBlockResponseDTO updateVocabBlock(UUID uuid, VocabBlockRequestDTO dto);
    VocabBlockResponseDTO getVocabBlockById(UUID uuid);
    List<VocabBlockResponseDTO> getAllVocabBlocks();
    void deleteVocabBlock(UUID uuid);

}
