package org.example.service.generation;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.Exercise;
import org.example.model.entity.TextBlock;
import org.example.model.enums.ExerciseType;
import org.example.service.ai.LlmClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ExerciseFactory {

    private final LlmClient llmClient;

    public List<Exercise> generateFromText(TextBlock textBlock) {
        List<Exercise> exercises = new ArrayList<>();

        String mcqPrompt = String.format(
                """
                        Create 3 multiple-choice questions based on the following text:
                        %s
                        Provide the question, 3–4 answer options, and indicate the correct answer.""",
                textBlock.getContent()
        );
        String mcqResponse = llmClient.generateText(mcqPrompt);

        exercises.add(Exercise.builder()
                .uuid(UUID.randomUUID())
                .pack(textBlock.getPack())
                .type(ExerciseType.MCQ)
                .prompt(mcqResponse)
                .maxScore(3)
                .build());

        String gapPrompt = String.format(
                """
                        Create 1 gap-fill exercise (fill-in-the-blank) using the following text:
                        %s
                        Provide the text with blanks and the correct words.""",
                textBlock.getContent()
        );
        String gapResponse = llmClient.generateText(gapPrompt);

        exercises.add(Exercise.builder()
                .uuid(UUID.randomUUID())
                .pack(textBlock.getPack())
                .type(ExerciseType.GAP_FILL)
                .prompt(gapResponse)
                .maxScore(5)
                .build());

        return exercises;
    }
}
