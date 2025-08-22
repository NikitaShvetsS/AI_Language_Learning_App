package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.DailyPackRequestDTO;
import org.example.dto.response.DailyPackResponseDTO;
import org.example.exception.UserNotFoundException;
import org.example.model.entity.User;
import org.example.repository.UserRepository;
import org.example.service.crud.DailyPackService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/daily-pack")
@RequiredArgsConstructor
public class DailyPackController {

    private final DailyPackService dailyPackService;
    private final UserRepository userRepository;

    @PostMapping("/create")
    public DailyPackResponseDTO createDailyPack(@RequestBody DailyPackRequestDTO dto){
        log.info("Running method create daily pack");
        return dailyPackService.createDailyPack(dto);
    }

    @PutMapping("/id/{uuid}")
    public DailyPackResponseDTO updateDailyPack(@PathVariable("uuid") UUID uuid,
                                                @RequestBody DailyPackRequestDTO dto){
        log.info("Running method update daily pack");
        return dailyPackService.updateDailyPack(uuid, dto);
    }

    @GetMapping("/all")
    public List<DailyPackResponseDTO> getAll(){
        log.info("Running method get all daily packs");
        return dailyPackService.getAllPacks();
    }

    @GetMapping("/id/{uuid}")
    public DailyPackResponseDTO getById(@PathVariable("uuid") UUID uuid){
        log.info("Running method get daily pack by id");
        return dailyPackService.getDailyPackById(uuid);
    }

    @DeleteMapping("/id/{uuid}")
    public ResponseEntity<Void> deleteById(@PathVariable("uuid") UUID uuid){
        log.info("Running method delete daily pack by id");
        dailyPackService.deletePack(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/today")
    public ResponseEntity<DailyPackResponseDTO> getTodayPack() {
        User user = getCurrentUser();
        DailyPackResponseDTO dto = dailyPackService.generateDailyPackForUser(user.getUuid());
        return ResponseEntity.ok(dto);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}
