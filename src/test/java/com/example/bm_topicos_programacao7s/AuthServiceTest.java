package com.example.bm_topicos_programacao7s;

import com.example.bm_topicos_programacao7s.auth.model.User;
import com.example.bm_topicos_programacao7s.auth.repository.UserRepository;
import com.example.bm_topicos_programacao7s.auth.enuns.UserRoleEnum;
import com.example.bm_topicos_programacao7s.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnUserDetailsWhenUserExists() {
        User user = new User("alice", "password", UserRoleEnum.USER);
        when(userRepository.findByLogin("alice")).thenReturn(user);

        UserDetails userDetails = authService.loadUserByUsername("alice");

        assertNotNull(userDetails);
        assertEquals("alice", userDetails.getUsername());
        verify(userRepository, times(1)).findByLogin("alice");
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {
        when(userRepository.findByLogin("bob")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            authService.loadUserByUsername("bob");
        });

        verify(userRepository, times(1)).findByLogin("bob");
    }
}
