package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.response.VocabBlockResponseDTO;
import org.example.exception.UserNotFoundException;
import org.example.model.entity.User;
import org.example.model.entity.VocabBlock;
import org.example.repository.UserRepository;
import org.example.repository.VocabBlockRepository;
import org.example.mapper.VocabBlockMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/vocab")
@RequiredArgsConstructor
public class VocabApiController {

    private final VocabBlockRepository vocabBlockRepository;
    private final UserRepository userRepository;
    private final VocabBlockMapper mapper;

    @GetMapping
    public List<VocabBlockResponseDTO> getVocab() {
        User user = currentUser();
        return vocabBlockRepository.findByUser(user).stream()
                .map(mapper::toDto)
                .toList();
    }

    @PostMapping("/{word}/mark-known")
    public ResponseEntity<?> markKnown(@PathVariable("word") String word, @RequestBody MarkKnownRequest request) {
        User user = currentUser();
        VocabBlock block = vocabBlockRepository.findByUser(user).stream()
                .filter(v -> v.getWord().equalsIgnoreCase(word))
                .findFirst().orElse(null);
        if (block == null) {
            return ResponseEntity.notFound().build();
        }
        block.setKnown(Boolean.TRUE.equals(request.known));
        vocabBlockRepository.save(block);
        return ResponseEntity.ok(mapper.toDto(block));
    }

    private User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public static class MarkKnownRequest {
        public Boolean known;
    }
}


