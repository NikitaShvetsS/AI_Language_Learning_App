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
public class VocabBlockResponseDTO {

    private UUID uuid;
    private String word;
    private String translation;
    private String speechPart;
    private int timesSeen;
    private boolean isKnown;
    private Instant addedAt;

}
