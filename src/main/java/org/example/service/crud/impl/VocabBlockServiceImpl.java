package org.example.service.crud.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.VocabBlockRequestDTO;
import org.example.dto.response.VocabBlockResponseDTO;
import org.example.exception.VocabBlockNotFoundException;
import org.example.model.entity.VocabBlock;
import org.example.repository.VocabBlockRepository;
import org.example.service.crud.VocabService;
import org.example.mapper.VocabBlockMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VocabBlockServiceImpl implements VocabService {

    private final VocabBlockRepository vocabBlockRepository;
    private final VocabBlockMapper mapper;
    @Override
    public VocabBlockResponseDTO createVocabBlock(VocabBlockRequestDTO dto) {
        VocabBlock vocabBlock = vocabBlockRepository.save(mapper.toEntity(dto));
        return mapper.toDto(vocabBlock);
    }

    @Override
    public VocabBlockResponseDTO updateVocabBlock(UUID uuid, VocabBlockRequestDTO dto) {
        VocabBlock vocabBlock = vocabBlockRepository.findById(uuid)
                .orElseThrow(() -> new VocabBlockNotFoundException("Vocab Block not found"));;

        vocabBlock.setWord(dto.getWord());
        vocabBlock.setSpeechPart(dto.getSpeechPart());
        vocabBlock.setTranslation(dto.getTranslation());

        vocabBlockRepository.save(vocabBlock);

        return mapper.toDto(vocabBlock);
    }

    @Override
    public VocabBlockResponseDTO getVocabBlockById(UUID uuid) {
        VocabBlock vocabBlock = vocabBlockRepository.findById(uuid)
                .orElseThrow(() -> new VocabBlockNotFoundException("Vocab block not found"));
        return mapper.toDto(vocabBlock);
    }

    @Override
    public List<VocabBlockResponseDTO> getAllVocabBlocks() {
        return vocabBlockRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public void deleteVocabBlock(UUID uuid) {
        vocabBlockRepository.deleteById(uuid);
    }
}
