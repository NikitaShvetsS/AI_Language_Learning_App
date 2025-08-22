package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.response.DailyPackResponseDTO;
import org.example.exception.UserNotFoundException;
import org.example.mapper.DailyPackMapper;
import org.example.model.entity.DailyPack;
import org.example.model.entity.User;
import org.example.repository.DailyPackRepository;
import org.example.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryApiController {

    private final DailyPackRepository dailyPackRepository;
    private final UserRepository userRepository;
    private final DailyPackMapper mapper;

    @GetMapping
    public List<DailyPackResponseDTO> listHistory() {
        User user = currentUser();
        return dailyPackRepository.findAll().stream()
                .filter(p -> p.getUser() != null && p.getUser().getUuid().equals(user.getUuid()))
                .sorted(Comparator.comparing(DailyPack::getPackDate, Comparator.nullsLast(Comparator.reverseOrder())))
                .map(mapper::toDto)
                .toList();
    }

    private User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName()).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}


