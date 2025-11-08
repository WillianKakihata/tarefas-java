package com.example.bm_topicos_programacao7s;

import com.example.bm_topicos_programacao7s.auth.enuns.UserRoleEnum;
import com.example.bm_topicos_programacao7s.auth.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreationAndGetters() {
        User user = new User("john", "secret", UserRoleEnum.USER);

        assertEquals("john", user.getUsername());
        assertEquals("secret", user.getPassword());
        assertEquals(UserRoleEnum.USER, user.getRole());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }

    @Test
    void testAuthoritiesForUserRole() {
        User user = new User("alice", "pass", UserRoleEnum.USER);
        var authorities = user.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    void testAuthoritiesForAdminRole() {
        User admin = new User("admin", "adminpass", UserRoleEnum.ADMIN);
        var authorities = admin.getAuthorities();
        assertEquals(2, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}

