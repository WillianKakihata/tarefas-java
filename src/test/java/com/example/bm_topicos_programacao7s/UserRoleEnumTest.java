package com.example.bm_topicos_programacao7s;

import com.example.bm_topicos_programacao7s.auth.enuns.UserRoleEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserRoleEnumTest {

    @Test
    void testEnumValues() {
        assertEquals("admin", UserRoleEnum.ADMIN.getRole());
        assertEquals("user", UserRoleEnum.USER.getRole());
    }
}
