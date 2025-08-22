package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressResponseDTO {

    private UUID uuid;
    private UUID packId;
    private boolean completed;
    private Instant completedAt;
    private Integer score;

}
