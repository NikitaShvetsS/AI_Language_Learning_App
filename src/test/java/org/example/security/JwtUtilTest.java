package org.example.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "jwt.secret=supersecretkeyfortest1234567890123456",
        "jwt.expiration=86400000"
})
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void generateToken_shouldReturnToken() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test@example.com", "password", List.of()
        );
        UUID userId = UUID.randomUUID();

        String token = jwtUtil.generateToken(userDetails, userId);

        assertNotNull(token);
    }
    @Test
    void extractUserId() {
    }

    @Test
    void validateToken() {
    }

}