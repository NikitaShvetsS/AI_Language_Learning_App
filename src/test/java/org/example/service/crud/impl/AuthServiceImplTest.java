package org.example.service.crud.impl;

import org.example.dto.request.RegisterRequestDTO;
import org.example.dto.response.AuthResponseDTO;
import org.example.mapper.UserMapper;
import org.example.model.entity.User;
import org.example.model.enums.LLevel;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private UserMapper mapper;

    @Test
    void login() {

    }

    @BeforeEach
    void cleanDatabase() {
        userRepository.deleteAll();
    }

    @Test
    void register_shouldReturnTokenAndSaveUser() {

        RegisterRequestDTO req = new RegisterRequestDTO();
        req.setFirstName("Test");
        req.setLastName("User");
        req.setEmail("test@example.com");
        req.setPassword("password123");
        req.setNativeLanguage("RU");
        req.setTargetLanguage("EN");
        req.setLevel("A2");


        AuthResponseDTO response = authService.register(req);


        assertNotNull(response, "Response не должен быть null");
        assertNotNull(response.getToken(), "JWT токен не должен быть null");
        assertTrue(response.getToken().length() > 10, "JWT токен слишком короткий");


        Optional<User> userOpt = userRepository.findByEmail("test@example.com");
        assertTrue(userOpt.isPresent(), "Пользователь должен сохраниться в базе");

        User savedUser = userOpt.get();
        assertEquals("Test", savedUser.getFirstName());
        assertEquals("User", savedUser.getLastName());
        assertEquals("RU", savedUser.getNativeLanguage());
        assertEquals("EN", savedUser.getTargetLanguage());
        assertEquals(LLevel.A2, savedUser.getLevel());
    }
}