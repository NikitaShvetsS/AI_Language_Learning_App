package org.example.service.crud.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.DailyPackRequestDTO;
import org.example.dto.response.DailyPackResponseDTO;
import org.example.exception.DailyPackNotFoundException;
import org.example.exception.UserNotFoundException;
import org.example.model.entity.*;
import org.example.model.enums.LLevel;
import org.example.repository.DailyPackRepository;
import org.example.repository.UserRepository;
import org.example.service.ai.impl.AIServiceImpl;
import org.example.service.crud.DailyPackService;
import org.example.mapper.DailyPackMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DailyPackServiceImpl implements DailyPackService {

    private final DailyPackRepository dailyPackRepository;
    private final DailyPackMapper mapper;
    private final UserRepository userRepository;
    private final AIServiceImpl aiService;

    @Override
    public DailyPackResponseDTO createDailyPack(DailyPackRequestDTO dto) {
        DailyPack pack = dailyPackRepository.save(mapper.toEntity(dto));
        return mapper.toDto(pack);
    }

    @Override
    public DailyPackResponseDTO updateDailyPack(UUID uuid, DailyPackRequestDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        DailyPack dailyPack = dailyPackRepository.findById(uuid)
                .orElseThrow(() -> new DailyPackNotFoundException("Daily Pack not found"));


        dailyPack.setPackDate(dto.getPackDate());
        dailyPack.setStatus(dto.getStatus());
        dailyPack.setUser(user);
        dailyPackRepository.save(dailyPack);

        return mapper.toDto(dailyPack);
    }

    @Override
    public DailyPackResponseDTO getDailyPackById(UUID uuid) {
        DailyPack pack = dailyPackRepository.findById(uuid)
                .orElseThrow(() -> new DailyPackNotFoundException("Daily pack not found"));
        return mapper.toDto(pack);
    }

    @Override
    public List<DailyPackResponseDTO> getAllPacks() {
        return dailyPackRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public void deletePack(UUID uuid) {
        dailyPackRepository.deleteById(uuid);
    }

    @Transactional
    public DailyPackResponseDTO generateDailyPackForUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        LLevel level = Optional.ofNullable(user.getLevel()).orElse(LLevel.A1);
        String topic = "general";

        DailyPack pack = aiService.generateDailyPack(user, topic, level);

        dailyPackRepository.save(pack);

        return mapper.toDto(pack);
    }
}
