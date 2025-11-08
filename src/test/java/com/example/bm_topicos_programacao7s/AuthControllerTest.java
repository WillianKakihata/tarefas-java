package com.example.bm_topicos_programacao7s;

import com.example.bm_topicos_programacao7s.auth.controller.AuthController;
import com.example.bm_topicos_programacao7s.auth.dto.AuthDTO;
import com.example.bm_topicos_programacao7s.auth.dto.LoginResponseDTO;
import com.example.bm_topicos_programacao7s.auth.dto.RegisterDTO;
import com.example.bm_topicos_programacao7s.auth.model.User;
import com.example.bm_topicos_programacao7s.auth.enuns.UserRoleEnum;
import com.example.bm_topicos_programacao7s.auth.repository.UserRepository;
import com.example.bm_topicos_programacao7s.auth.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_shouldReturnToken_whenCredentialsValid() {
        AuthDTO authDTO = new AuthDTO("admin", "1234");
        User user = new User("admin", "hashedPass", UserRoleEnum.ADMIN);
        Authentication auth = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(user);
        when(tokenService.generateToken(user)).thenReturn("token123");

        ResponseEntity<?> response = authController.login(authDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof LoginResponseDTO);
        assertEquals("token123", ((LoginResponseDTO) response.getBody()).token());
    }

    @Test
    void register_shouldSaveUser_whenLoginNotExists() {
        RegisterDTO registerDTO = new RegisterDTO("newUser", "1234", UserRoleEnum.USER);
        when(userRepository.findByLogin("newUser")).thenReturn(null);

        ResponseEntity<?> response = authController.register(registerDTO);

        assertEquals(200, response.getStatusCodeValue());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void register_shouldReturnBadRequest_whenLoginExists() {
        RegisterDTO registerDTO = new RegisterDTO("existingUser", "1234", UserRoleEnum.ADMIN);
        when(userRepository.findByLogin("existingUser"))
                .thenReturn(new User("existingUser", "hashedPass", UserRoleEnum.ADMIN));

        ResponseEntity<?> response = authController.register(registerDTO);

        assertEquals(400, response.getStatusCodeValue());
        verify(userRepository, never()).save(any(User.class));
    }
}
