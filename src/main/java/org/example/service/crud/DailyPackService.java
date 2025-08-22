package org.example.service.crud;

import org.example.dto.request.DailyPackRequestDTO;
import org.example.dto.response.DailyPackResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DailyPackService {

    DailyPackResponseDTO createDailyPack(DailyPackRequestDTO dto);
    DailyPackResponseDTO updateDailyPack(UUID uuid, DailyPackRequestDTO dto);
    DailyPackResponseDTO getDailyPackById(UUID uuid);
    List<DailyPackResponseDTO> getAllPacks();
    void deletePack(UUID uuid);
    DailyPackResponseDTO generateDailyPackForUser(UUID userId);

}
