package org.example.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.ProgressRequestDTO;
import org.example.dto.response.ProgressResponseDTO;
import org.example.exception.DailyPackNotFoundException;
import org.example.model.entity.DailyPack;
import org.example.model.entity.Progress;
import org.example.repository.DailyPackRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProgressMapper {

    private final DailyPackRepository repository;

    public Progress toEntity(ProgressRequestDTO dto){

        DailyPack pack = repository.findById(dto.getPackId())
                .orElseThrow(() -> new DailyPackNotFoundException("Pack not found"));

        return Progress.builder()
                .uuid(UUID.randomUUID())
                .pack(pack)
                .score(dto.getScore())
                .build();
    }

    public ProgressResponseDTO toDto(Progress progress){

        return ProgressResponseDTO.builder()
                .uuid(progress.getUuid())
                .completed(progress.isCompleted())
                .completedAt(progress.getCompletedAt())
                .packId(progress.getPack().getUuid())
                .score(progress.getScore())
                .build();

    }

}
