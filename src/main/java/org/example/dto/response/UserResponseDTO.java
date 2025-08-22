package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enums.LLevel;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private UUID uuid;
    private String name;
    private String email;
    private String nativeLanguage;
    private String targetLanguage;
    private LLevel level;

}
