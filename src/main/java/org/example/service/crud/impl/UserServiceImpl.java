package org.example.service.crud.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.UserRequestDTO;
import org.example.dto.response.UserResponseDTO;
import org.example.exception.UserNotFoundException;
import org.example.model.entity.User;
import org.example.repository.UserRepository;
import org.example.service.crud.UserService;
import org.example.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = userRepository.save(mapper.toEntity(dto));
        return mapper.toDto(user);
    }

    @Override
    public UserResponseDTO updateUser(UUID uuid, UserRequestDTO dto) {

        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setEmail(dto.getEmail());
        user.setLevel(dto.getLevel());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setNativeLanguage(dto.getNativeLanguage());
        user.setTargetLanguage(dto.getTargetLanguage());

        userRepository.save(user);

        return mapper.toDto(user);
    }

    @Override
    public UserResponseDTO getUserById(UUID uuid) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.toDto(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public void deleteUser(UUID uuid) {
        userRepository.deleteById(uuid);
    }
}
