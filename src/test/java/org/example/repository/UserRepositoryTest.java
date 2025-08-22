package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.model.entity.User;
import org.example.model.enums.LLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testSave() {
        User user = User.builder()
                .firstName("Test")
                .lastName("User")
                .email("test@example.com")
                .password("pass123")
                .nativeLanguage("RU")
                .targetLanguage("EN")
                .level(LLevel.A2)
                .build();

        User saved = userRepository.save(user);
        System.out.println("Saved user: " + saved);
        assertNotNull(saved);
        assertNotNull(saved.getUuid());
    }

    @Test
    void findByEmail() {
    }
}