package com.example.bm_topicos_programacao7s;
import com.example.bm_topicos_programacao7s.auth.model.User;
import com.example.bm_topicos_programacao7s.auth.repository.UserRepository;
import com.example.bm_topicos_programacao7s.auth.enuns.UserRoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // perfil que usa o banco PostgreSQL de teste
@Transactional
class UserRepositoryPostgresTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testFindByLogin() {
        User user = new User("bob", "secret", UserRoleEnum.USER);
        userRepository.save(user);

        User found = (User) userRepository.findByLogin("bob");
        assertNotNull(found);
        assertEquals("bob", found.getLogin());
    }
}