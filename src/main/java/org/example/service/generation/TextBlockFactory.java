package org.example.service.generation;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.TextBlock;
import org.example.model.entity.User;
import org.example.model.enums.LLevel;
import org.example.service.ai.LlmClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TextBlockFactory {

    private final LlmClient llmClient;

    public TextBlock generate(User user, String topic, LLevel level) {
        String prompt = String.format(
                "Write a short %s-level text (~5 sentences) on the topic '%s' in English. "
                        + "Use clear and simple language suitable for language learners.",
                level, topic
        );

        String content = llmClient.generateText(prompt);

        return TextBlock.builder()
                .uuid(UUID.randomUUID())
                .language("EN")
                .level(level)
                .content(content)
                .build();
    }
}