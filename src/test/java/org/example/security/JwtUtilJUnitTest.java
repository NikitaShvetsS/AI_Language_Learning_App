package org.example.security;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class JwtUtilJUnitTest {
    @Test
    void generateToken_shouldReturnToken() {
        JwtUtil jwtUtil = new JwtUtil("supersecretkeyfortest1234567890123456", 86400000L);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test@example.com", "password", List.of()
        );
        UUID userId = UUID.randomUUID();

        String token = jwtUtil.generateToken(userDetails, userId);

        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token, userDetails));
    }
}
