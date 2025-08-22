package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.VocabBlockRequestDTO;
import org.example.dto.response.VocabBlockResponseDTO;
import org.example.service.crud.VocabService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/vocab_block")
@RequiredArgsConstructor
public class VocabBlockController {

    private final VocabService vocabService;

    @PostMapping("/create")
    public VocabBlockResponseDTO createUser(@RequestBody VocabBlockRequestDTO dto){
        log.info("Running method create vocab block");
        return vocabService.createVocabBlock(dto);
    }

    @PutMapping("/update/{uuid}")
    public VocabBlockResponseDTO updateUser(@PathVariable("uuid") UUID uuid, @RequestBody VocabBlockRequestDTO dto){
        log.info("Running method update vocab block");
        return vocabService.updateVocabBlock(uuid, dto);
    }

    @GetMapping("/all")
    public List<VocabBlockResponseDTO> getAll(){
        log.info("Running method get all vocab blocks");
        return vocabService.getAllVocabBlocks();
    }

    @GetMapping("/id/{uuid}")
    public VocabBlockResponseDTO getById(@PathVariable("uuid") UUID uuid){
        log.info("Running method get vocab block by id");
        return vocabService.getVocabBlockById(uuid);
    }

    @DeleteMapping("/id/{uuid}")
    public ResponseEntity<Void> deleteById(@PathVariable("uuid") UUID uuid){
        log.info("Running method delete vocab block by id");
        vocabService.deleteVocabBlock(uuid);
        return ResponseEntity.ok().build();
    }

}
