package org.example.service.generation;

import org.example.model.enums.LLevel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromptBuilder {

    public String buildTextPrompt(String topic, LLevel level, List<String> vocab) {
        String vocabString = String.join(", ", vocab);
        return String.format(
                "Generate a short %s-level text (5 sentences) on topic '%s'. Include these words: %s. Provide clear context for each word.",
                level, topic, vocabString
        );
    }

    public String buildExercisePrompt(String text, LLevel level) {
        return String.format(
                "Based on this text: '%s', create 3 comprehension exercises suitable for %s level students. Provide prompt and type of exercise.",
                text, level
        );
    }

    public String buildVocabPrompt(String topic, LLevel level) {
        return String.format(
                "Generate 5 new words on topic '%s' for %s level, with translation and part of speech in JSON format.",
                topic, level
        );
    }

}
