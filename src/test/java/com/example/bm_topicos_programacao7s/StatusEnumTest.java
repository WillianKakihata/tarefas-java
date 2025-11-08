package com.example.bm_topicos_programacao7s;
import com.example.bm_topicos_programacao7s.tarefas.enuns.StatusEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class StatusEnumTest {

    @Test
    void testAllStatusValues() {
        assertEquals(0, StatusEnum.EM_PROCESSO.getStatus(), "EM_PROCESSO deve ser 0");
        assertEquals(1, StatusEnum.INICIO.getStatus(), "INICIO deve ser 1");
        assertEquals(2, StatusEnum.FINALIZADO.getStatus(), "FINALIZADO deve ser 2");
    }
}