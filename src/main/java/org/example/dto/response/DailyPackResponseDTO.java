package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enums.PackStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyPackResponseDTO {

    private UUID uuid;
    private LocalDate packDate;
    private PackStatus status;
    private String modelUsed;
    private List<TextBlockResponseDTO> textBlocks;
    private List<ExerciseResponseDTO> exercises;
    private List<VocabBlockResponseDTO> vocabBlock;
    private Instant createdAt;

}
