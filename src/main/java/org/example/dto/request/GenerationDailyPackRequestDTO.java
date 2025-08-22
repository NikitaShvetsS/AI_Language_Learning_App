package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enums.LLevel;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerationDailyPackRequestDTO {

    private UUID userId;
    private String topic;
    private LLevel level;
    private int dailyWords;

}
