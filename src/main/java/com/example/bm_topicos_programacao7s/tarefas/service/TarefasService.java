package com.example.bmTopicosProgramacao7s.tarefas.service;
import com.example.bmTopicosProgramacao7s.tarefas.dto.TarefasDTO;
import com.example.bmTopicosProgramacao7s.tarefas.model.Tarefas;
import com.example.bmTopicosProgramacao7s.tarefas.repository.TarefasRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefasService {
    @Autowired
    TarefasRepository tarefasRepository;
    @Autowired
    ModelMapper modelMapper;
    public TarefasDTO create(TarefasDTO tarefas){
        Tarefas novaTarefa = modelMapper.map(tarefas, Tarefas.class);
        novaTarefa = this.tarefasRepository.save(novaTarefa);
        return modelMapper.map(novaTarefa, TarefasDTO.class);
    }

    public TarefasDTO update(TarefasDTO novatarefa, Long Id) {
        Tarefas tarefas = tarefasRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Task not found for update"));
        if(tarefas != null) {
            tarefas.setTitulo(novatarefa.getTitulo());
            tarefas.setDescricao(novatarefa.getDescricao());
            tarefas.setStatus(novatarefa.getStatus());
            tarefasRepository.save(tarefas);
            return modelMapper.map(tarefas, TarefasDTO.class);
        }
        return modelMapper.map(tarefas, TarefasDTO.class);
    }

    public List<TarefasDTO> findall() {
        List<Tarefas> tarefas = tarefasRepository.findAll();
        return modelMapper.map(tarefas, new TypeToken<List<TarefasDTO>>(){}.getType());
    }

    public TarefasDTO findById(Long id) {
        Tarefas tarefas = tarefasRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found for get"));
        return modelMapper.map(tarefas, TarefasDTO.class);
    }

    public TarefasDTO delete(Long id) {
        Tarefas tarefas = tarefasRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found for deletion"));
        tarefasRepository.deleteById(id);
        return modelMapper.map(tarefas, TarefasDTO.class);
    }
}
