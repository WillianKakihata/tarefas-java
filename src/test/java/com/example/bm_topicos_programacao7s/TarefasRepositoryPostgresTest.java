package com.example.bm_topicos_programacao7s;
import com.example.bm_topicos_programacao7s.tarefas.enuns.StatusEnum;
import com.example.bm_topicos_programacao7s.tarefas.model.Tarefas;
import com.example.bm_topicos_programacao7s.tarefas.repository.TarefasRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // garante rollback após cada teste
class TarefasRepositoryPostgresTest {

    @Autowired
    private TarefasRepository tarefasRepository;

    @Test
    void testSaveAndFindTarefa() {
        Tarefas tarefa = new Tarefas();
        tarefa.setTitulo("Teste PostgreSQL");
        tarefa.setDescricao("Descrição teste");
        tarefa.setStatus(StatusEnum.FINALIZADO);

        Tarefas saved = tarefasRepository.save(tarefa);
        Tarefas found = tarefasRepository.findById(saved.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getTitulo()).isEqualTo("Teste PostgreSQL");
        assertThat(found.getDescricao()).isEqualTo("Descrição teste");
        assertThat(found.getStatus()).isEqualTo(StatusEnum.FINALIZADO);
    }
}
