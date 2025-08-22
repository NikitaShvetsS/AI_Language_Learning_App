package org.example.service.ai.impl;

import lombok.RequiredArgsConstructor;
import org.example.config.OpenAIProperties;
import org.example.service.ai.LlmClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIClient implements LlmClient {

    private final OpenAIProperties properties;
    private final RestTemplate restTemplate = new RestTemplate();

    public String generateText(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "model", properties.getModel(),
                "prompt", prompt,
                "max_tokens", 300
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(properties.getApiKey());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        Map response = restTemplate.postForObject(properties.getApiUrl(), request, Map.class);
        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
        if (choices != null && !choices.isEmpty()) {
            return (String) choices.get(0).get("text");
        }
        return "";
    }

}
