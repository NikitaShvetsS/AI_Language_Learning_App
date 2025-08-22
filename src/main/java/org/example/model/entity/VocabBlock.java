package org.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VocabBlock {

    @Id
    @GeneratedValue
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid")
    private User user;
    private String word;
    private String translation;
    private String speechPart;
    @ManyToOne
    @JoinColumn(name = "source_pack_id")
    private DailyPack sourcePack;
    private int timesSeen;
    private boolean isKnown;
    private Instant addedAt;

}
