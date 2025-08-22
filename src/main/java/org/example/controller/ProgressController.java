package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.ProgressRequestDTO;
import org.example.dto.response.ProgressResponseDTO;
import org.example.service.crud.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/create")
    public ProgressResponseDTO createProgress(@RequestBody ProgressRequestDTO dto){
        log.info("Running method create progress");
        return progressService.createProgress(dto);
    }

    @PutMapping("/update/{uuid}")
    public ProgressResponseDTO updateProgress(@PathVariable("uuid") UUID uuid,
                                              @RequestBody ProgressRequestDTO dto){
        log.info("Running method update progress");
        return progressService.updateProgress(uuid, dto);
    }

    @GetMapping("/all")
    public List<ProgressResponseDTO> getAll(){
        log.info("Running method get all progresses");
        return progressService.getAllProgress();
    }

    @GetMapping("/id/{uuid}")
    public ProgressResponseDTO getById(@PathVariable("uuid") UUID uuid){
        log.info("Running method get progress by id");
        return progressService.getProgressById(uuid);
    }

    @DeleteMapping("/id/{uuid}")
    public ResponseEntity<Void> deleteById(@PathVariable("uuid") UUID uuid){
        log.info("Running method delete progress by id");
        progressService.deleteProgress(uuid);
        return ResponseEntity.ok().build();
    }


}
