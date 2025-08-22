package org.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enums.PackStatus;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyPack {

    @Id
    @GeneratedValue
    private UUID uuid;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "uuid")
    private User user;
    private LocalDate packDate;
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status", columnDefinition = "pack_status")
    private PackStatus status;
    private String modelUsed;
    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TextBlock> textBlock = new ArrayList<>();
    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> exercises = new ArrayList<>();
    private Instant createdAt;

}
