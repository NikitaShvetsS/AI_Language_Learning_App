package org.example.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.UserRequestDTO;
import org.example.dto.response.UserResponseDTO;
import org.example.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toEntity(UserRequestDTO dto){

        return User.builder()
                .uuid(UUID.randomUUID())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .nativeLanguage(dto.getNativeLanguage())
                .targetLanguage(dto.getTargetLanguage())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

    }

    public UserResponseDTO toDto(User user){

        return UserResponseDTO.builder()
                .email(user.getEmail())
                .uuid(user.getUuid())
                .level(user.getLevel())
                .name(user.getFirstName().concat(" " + user.getLastName()))
                .nativeLanguage(user.getNativeLanguage())
                .targetLanguage(user.getTargetLanguage())
                .build();

    }

}
