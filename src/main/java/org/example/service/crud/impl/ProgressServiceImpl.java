package org.example.service.crud.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.ProgressRequestDTO;
import org.example.dto.response.ProgressResponseDTO;
import org.example.exception.DailyPackNotFoundException;
import org.example.exception.ProgressNotFoundException;
import org.example.model.entity.DailyPack;
import org.example.model.entity.Progress;
import org.example.repository.DailyPackRepository;
import org.example.repository.ProgressRepository;
import org.example.service.crud.ProgressService;
import org.example.mapper.ProgressMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final ProgressMapper mapper;
    private final DailyPackRepository dailyPackRepository;

    @Override
    public ProgressResponseDTO createProgress(ProgressRequestDTO dto) {
        Progress progress = progressRepository.save(mapper.toEntity(dto));
        return mapper.toDto(progress);
    }

    @Override
    public ProgressResponseDTO updateProgress(UUID uuid, ProgressRequestDTO dto) {

        DailyPack pack = dailyPackRepository.findById(dto.getPackId())
                .orElseThrow(() -> new DailyPackNotFoundException("Pack not found"));

        Progress progress = progressRepository.findById(uuid)
                .orElseThrow(() -> new ProgressNotFoundException("Progress not found"));

        progress.setPack(pack);
        progress.setScore(dto.getScore());

        progressRepository.save(progress);

        return mapper.toDto(progress);
    }

    @Override
    public ProgressResponseDTO getProgressById(UUID uuid) {
        Progress progress = progressRepository.findById(uuid)
                .orElseThrow(() -> new ProgressNotFoundException("Progress not found"));
        return mapper.toDto(progress);
    }

    @Override
    public List<ProgressResponseDTO> getAllProgress() {
        return progressRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public void deleteProgress(UUID uuid) {
        progressRepository.deleteById(uuid);
    }
}
