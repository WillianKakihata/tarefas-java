package com.example.bm_topicos_programacao7s;
import com.example.bm_topicos_programacao7s.tarefas.dto.TarefasDTO;
import com.example.bm_topicos_programacao7s.tarefas.enuns.StatusEnum;
import com.example.bm_topicos_programacao7s.tarefas.model.Tarefas;
import com.example.bm_topicos_programacao7s.tarefas.repository.TarefasRepository;
import com.example.bm_topicos_programacao7s.tarefas.service.TarefasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TarefasServiceTest {

    @Mock
    private TarefasRepository tarefasRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TarefasService tarefasService;

    private Tarefas tarefa;
    private TarefasDTO tarefaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tarefa = new Tarefas(null, "Teste", "Descrição", StatusEnum.INICIO);
        tarefaDTO = new TarefasDTO("Teste", "Descrição", StatusEnum.INICIO);
    }

    @Test
    void testCreate() {
        when(modelMapper.map(tarefaDTO, Tarefas.class)).thenReturn(tarefa);
        when(tarefasRepository.save(tarefa)).thenAnswer(i -> {
            tarefa.setId(1L);
            return tarefa;
        });
        when(modelMapper.map(tarefa, TarefasDTO.class)).thenReturn(tarefaDTO);

        TarefasDTO result = tarefasService.create(tarefaDTO);

        assertThat(result).isEqualTo(tarefaDTO);
        verify(tarefasRepository, times(1)).save(tarefa);
    }

    @Test
    void testFindById() {
        when(tarefasRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(modelMapper.map(tarefa, TarefasDTO.class)).thenReturn(tarefaDTO);

        TarefasDTO result = tarefasService.findById(1L);

        assertThat(result).isEqualTo(tarefaDTO);
    }

    @Test
    void testFindByIdNotFound() {
        when(tarefasRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> tarefasService.findById(1L));
    }

    @Test
    void testFindAll() {
        List<Tarefas> lista = Arrays.asList(tarefa);
        when(tarefasRepository.findAll()).thenReturn(lista);
        when(modelMapper.map(lista, new TypeToken<List<TarefasDTO>>(){}.getType())).thenReturn(Arrays.asList(tarefaDTO));

        List<TarefasDTO> result = tarefasService.findall();

        assertThat(result).containsExactly(tarefaDTO);
    }

    @Test
    void testUpdate() {
        TarefasDTO updateDTO = new TarefasDTO("Novo titulo", "Nova desc", StatusEnum.FINALIZADO);
        Tarefas updated = new Tarefas(1L, "Novo titulo", "Nova desc", StatusEnum.FINALIZADO);

        when(tarefasRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(modelMapper.map(tarefa, TarefasDTO.class)).thenReturn(updateDTO);

        TarefasDTO result = tarefasService.update(updateDTO, 1L);

        assertThat(result).isEqualTo(updateDTO);
        verify(tarefasRepository, times(1)).save(tarefa);
    }

    @Test
    void testUpdateNotFound() {
        when(tarefasRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> tarefasService.update(tarefaDTO, 1L));
    }

    @Test
    void testDelete() {
        when(tarefasRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(modelMapper.map(tarefa, TarefasDTO.class)).thenReturn(tarefaDTO);

        TarefasDTO result = tarefasService.delete(1L);

        assertThat(result).isEqualTo(tarefaDTO);
        verify(tarefasRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        when(tarefasRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> tarefasService.delete(1L));
    }
}