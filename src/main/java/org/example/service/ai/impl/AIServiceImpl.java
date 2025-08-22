package org.example.service.ai.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.model.entity.*;
import org.example.model.enums.LLevel;
import org.example.model.enums.PackStatus;
import org.example.service.ai.LlmClient;
import org.example.service.generation.ExerciseFactory;
import org.example.service.generation.PromptBuilder;
import org.example.service.generation.TextBlockFactory;
import org.example.repository.VocabBlockRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIServiceImpl {

    private final LlmClient llmClient;
    private final PromptBuilder promptBuilder;
    private final TextBlockFactory textBlockFactory;
    private final ExerciseFactory exerciseFactory;
    private final VocabBlockRepository vocabBlockRepository;

    public List<VocabBlock> generateVocabBlocks(User user, String topic, LLevel level) {
        String prompt = promptBuilder.buildVocabPrompt(topic, level);
        String response = llmClient.generateText(prompt);

        List<VocabBlock> vocabBlocks = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, String>> words = mapper.readValue(response,
                    new TypeReference<>() {});
            for (Map<String, String> wordMap : words) {
                VocabBlock block = VocabBlock.builder()
                        .user(user)
                        .word(wordMap.get("word"))
                        .translation(wordMap.get("translation"))
                        .speechPart(wordMap.get("speechPart"))
                        .addedAt(Instant.now())
                        .timesSeen(0)
                        .isKnown(false)
                        .build();
                vocabBlocks.add(block);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vocabBlocks;
    }

    public TextBlock generateTextBlock(DailyPack pack, String topic, LLevel level) {
        TextBlock textBlock = textBlockFactory.generate(pack.getUser(), topic, level);
        textBlock.setPack(pack);
        return textBlock;
    }

    public List<Exercise> generateExercises(DailyPack pack, TextBlock textBlock) {
        List<Exercise> exercises = exerciseFactory.generateFromText(textBlock);
        exercises.forEach(e -> e.setPack(pack));
        return exercises;
    }

    public DailyPack generateDailyPack(User user, String topic, LLevel level) {
            DailyPack pack = DailyPack.builder()
                    .user(user)
                    .packDate(LocalDate.now())
                    .status(PackStatus.READY)
                    .createdAt(Instant.now())
                    .modelUsed("AI: " + llmClient.getClass().getSimpleName())
                    .build();


            TextBlock textBlock = generateTextBlock(pack, topic, level);
            List<Exercise> exercises = generateExercises(pack, textBlock);
            List<VocabBlock> vocab = generateVocabBlocks(user, topic, level);
            vocab.forEach(v -> v.setSourcePack(pack));

            pack.setTextBlock(List.of(textBlock));
            pack.setExercises(exercises);
            vocabBlockRepository.saveAll(vocab);

            return pack;
    }

}
