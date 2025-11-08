
package com.example.bm_topicos_programacao7s;

import com.example.bm_topicos_programacao7s.auth.component.SecurityFilter;
import com.example.bm_topicos_programacao7s.auth.repository.UserRepository;
import com.example.bm_topicos_programacao7s.auth.service.TokenService;
import com.example.bm_topicos_programacao7s.auth.model.User;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityFilterTest {

    @InjectMocks
    SecurityFilter securityFilter;

    @Mock
    TokenService tokenService;

    @Mock
    UserRepository userRepository;

    @Mock
    FilterChain filterChain;

    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldAuthenticateUserWhenTokenIsValid() throws Exception {
        // Arrange
        String token = "valid-token";
        request.addHeader("Authorization", "Bearer " + token);
        String login = "admin";
        User user = mock(User.class);
        when(tokenService.validateToken(token)).thenReturn(login);
        when(userRepository.findByLogin(login)).thenReturn(user);
        when(user.getAuthorities()).thenReturn(null);

        // Act
        securityFilter.doFilter(request, response, filterChain);

        // Assert
        SecurityContext context = SecurityContextHolder.getContext();
        assertNotNull(context.getAuthentication());
        assertEquals(user, context.getAuthentication().getPrincipal());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void shouldReturn401WhenTokenIsInvalid() throws Exception {
        // Arrange
        String token = "invalid-token";
        request.addHeader("Authorization", "Bearer " + token);
        when(tokenService.validateToken(token)).thenThrow(new RuntimeException("Invalid"));

        // Act
        securityFilter.doFilter(request, response, filterChain);

        // Assert
        assertEquals(401, response.getStatus());
        assertTrue(response.getContentAsString().contains("Token inválido"));
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    void shouldContinueFilterWhenNoToken() throws Exception {
        // Act
        securityFilter.doFilter(request, response, filterChain);

        // Assert
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }
}
