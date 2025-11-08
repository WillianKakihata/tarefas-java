package com.example.bm_topicos_programacao7s;

import com.example.bm_topicos_programacao7s.auth.enuns.UserRoleEnum;
import com.example.bm_topicos_programacao7s.auth.model.User;
import com.example.bm_topicos_programacao7s.auth.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        tokenService = new TokenService();
        // injeta o secret no service
        ReflectionTestUtils.setField(tokenService, "secret", "my-secret-key");
    }

    @Test
    void generateToken_shouldReturnNonEmptyString() {
        User user = new User("john", "pass", UserRoleEnum.USER);
        String token = tokenService.generateToken(user);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void validateToken_shouldReturnUsername() {
        User user = new User("john", "pass", UserRoleEnum.USER);
        String token = tokenService.generateToken(user);
        String username = tokenService.validateToken(token);
        assertEquals("john", username);
    }

    @Test
    void validateToken_shouldReturnEmptyForInvalidToken() {
        String result = tokenService.validateToken("invalid.token.here");
        assertEquals("", result);
    }
}
