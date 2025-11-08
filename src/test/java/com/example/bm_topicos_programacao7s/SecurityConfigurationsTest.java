package com.example.bm_topicos_programacao7s;


import com.example.bm_topicos_programacao7s.tarefas.enuns.StatusEnum;
import com.example.bm_topicos_programacao7s.tarefas.model.Tarefas;
import com.example.bm_topicos_programacao7s.tarefas.repository.TarefasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigurationsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TarefasRepository tarefasRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Tarefas tarefaExistente;

    @BeforeEach
    void setup() {
        // Limpa o banco antes de cada teste
        tarefasRepository.deleteAll();

        // Cria uma tarefa para teste de GET
        tarefaExistente = new Tarefas();
        tarefaExistente.setTitulo("Tarefa de Teste");
        tarefaExistente.setDescricao("Descrição da tarefa");
        tarefaExistente.setStatus(StatusEnum.EM_PROCESSO);
        tarefaExistente = tarefasRepository.save(tarefaExistente);
    }

    // Teste de acesso sem autenticação deve retornar 403 (Forbidden) se a configuração do Spring Security exigir login
    @Test
    void acessoSemAutenticacao_deveRetornar403() throws Exception {
        mockMvc.perform(get("/tarefas/" + tarefaExistente.getId()))
                .andExpect(status().isForbidden()); // Ajustado para 403
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void acessoComUsuarioAutenticado_deveRetornar200() throws Exception {
        mockMvc.perform(get("/tarefas/" + tarefaExistente.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void criarTarefa_deveRetornar201() throws Exception {
        Tarefas novaTarefa = new Tarefas();
        novaTarefa.setTitulo("Nova Tarefa");
        novaTarefa.setDescricao("Descrição");
        novaTarefa.setStatus(StatusEnum.INICIO);

        mockMvc.perform(post("/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novaTarefa)))
                .andExpect(status().isCreated());
    }
}
