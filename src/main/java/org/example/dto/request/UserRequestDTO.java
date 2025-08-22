package org.example.dto.request;

import lombok.*;
import org.example.model.enums.LLevel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String nativeLanguage;
    private String targetLanguage;
    private LLevel level;

}
