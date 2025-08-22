package org.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enums.ExerciseType;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

    @Id
    @GeneratedValue
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "pack_id", nullable = false)
    private DailyPack pack;
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "type", columnDefinition = "exercise_type")
    private ExerciseType type;
    private String prompt;
    private String optionalJson;
    private String answer;
    private int maxScore;
    private Instant createdAt;

}
