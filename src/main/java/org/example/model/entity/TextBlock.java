package org.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enums.LLevel;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextBlock {

    @Id
    @GeneratedValue
    private UUID uuid;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pack_id", nullable = false)
    private DailyPack pack;
    private String language;
    @Column(columnDefinition = "text", nullable = false)
    private String content;
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "level")
    private LLevel level;

}
