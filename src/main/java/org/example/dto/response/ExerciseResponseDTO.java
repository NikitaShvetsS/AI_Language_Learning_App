package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enums.ExerciseType;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseResponseDTO {

    private UUID uuid;
    private ExerciseType type;
    private String prompt;
    private int maxScore;

}
