package org.example.service.crud;

import org.example.dto.request.ProgressRequestDTO;
import org.example.dto.response.ProgressResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProgressService {

    ProgressResponseDTO createProgress(ProgressRequestDTO dto);
    ProgressResponseDTO updateProgress(UUID uuid, ProgressRequestDTO dto);
    ProgressResponseDTO getProgressById(UUID uuid);
    List<ProgressResponseDTO> getAllProgress();
    void deleteProgress(UUID uuid);

}
