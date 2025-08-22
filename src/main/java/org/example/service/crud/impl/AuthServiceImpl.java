package org.example.service.crud.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.AuthRequestDTO;
import org.example.dto.request.RegisterRequestDTO;
import org.example.dto.response.AuthResponseDTO;
import org.example.exception.EmailAlreadyRegisteredException;
import org.example.exception.UserNotFoundException;
import org.example.model.entity.User;
import org.example.model.enums.LLevel;
import org.example.repository.UserRepository;
import org.example.security.JwtUtil;
import org.example.service.crud.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO login(AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();

        String token = jwtUtil.generateToken(userDetails, user.getUuid());

        return new AuthResponseDTO(token, "Login successful");
    }


    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("User with this email already exists");
        }
        log.info("Register called with email={}", request.getEmail());

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nativeLanguage(request.getNativeLanguage())
                .targetLanguage(request.getTargetLanguage())
                .level(LLevel.valueOf(request.getLevel()))
                .build();

        User saved = userRepository.save(user);
        log.info("User saved: {}", user);

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(saved.getEmail())
                .password(saved.getPassword())
                .build();

        String token = jwtUtil.generateToken(userDetails, saved.getUuid());


        log.info("Generated token: {}", token);

        return new AuthResponseDTO(token, "Registration successful");
    }
}
