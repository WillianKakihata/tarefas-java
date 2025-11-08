package com.example.bm_topicos_programacao7s;

import com.example.bm_topicos_programacao7s.tarefas.controller.TarefasController;
import com.example.bm_topicos_programacao7s.tarefas.dto.TarefasDTO;
import com.example.bm_topicos_programacao7s.tarefas.enuns.StatusEnum;
import com.example.bm_topicos_programacao7s.tarefas.service.TarefasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class TarefasControllerTest {

    @Mock
    private TarefasService tarefasService;

    @InjectMocks
    private TarefasController tarefasController;

    private TarefasDTO tarefaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tarefaDTO = new TarefasDTO();
        tarefaDTO.setTitulo("Teste de tarefa");
        tarefaDTO.setDescricao("Descrição da tarefa");
        tarefaDTO.setStatus(StatusEnum.INICIO);
    }

    @Test
    void testCreate() {
        when(tarefasService.create(any(TarefasDTO.class))).thenReturn(tarefaDTO);

        ResponseEntity<TarefasDTO> response = tarefasController.create(tarefaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tarefaDTO, response.getBody());
        verify(tarefasService, times(1)).create(any(TarefasDTO.class));
    }

    @Test
    void testFindById() {
        when(tarefasService.findById(1L)).thenReturn(tarefaDTO);

        ResponseEntity<TarefasDTO> response = tarefasController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefaDTO, response.getBody());
        verify(tarefasService, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        List<TarefasDTO> lista = Arrays.asList(tarefaDTO);
        when(tarefasService.findall()).thenReturn(lista);

        ResponseEntity<List<TarefasDTO>> response = tarefasController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lista, response.getBody());
        verify(tarefasService, times(1)).findall();
    }

    @Test
    void testDelete() {
        when(tarefasService.delete(1L)).thenReturn(tarefaDTO);

        ResponseEntity<TarefasDTO> response = tarefasController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(tarefaDTO, response.getBody());
        verify(tarefasService, times(1)).delete(1L);
    }

    @Test
    void testUpdate() {
        when(tarefasService.update(any(TarefasDTO.class), eq(1L))).thenReturn(tarefaDTO);

        ResponseEntity<TarefasDTO> response = tarefasController.update(1L, tarefaDTO);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(tarefaDTO, response.getBody());
        verify(tarefasService, times(1)).update(any(TarefasDTO.class), eq(1L));
    }
}
