package com.example.bm_topicos_programacao7s.tarefas.controller;

import com.example.bm_topicos_programacao7s.tarefas.dto.TarefasDTO;
import com.example.bm_topicos_programacao7s.tarefas.service.TarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    @Autowired
    TarefasService tarefasService;

    @PostMapping()
    public ResponseEntity<TarefasDTO> create(@RequestBody TarefasDTO tarefas) {
        TarefasDTO tarefa = this.tarefasService.create(tarefas);
        return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefasDTO> findById(@PathVariable Long id) {
        TarefasDTO tarefa = this.tarefasService.findById(id);
        return new ResponseEntity<>(tarefa, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TarefasDTO>> findAll() {
        List<TarefasDTO> tarefa = this.tarefasService.findall();
        return new ResponseEntity<>(tarefa, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TarefasDTO> delete(@PathVariable Long id) {
        TarefasDTO tarefa = this.tarefasService.delete(id);
        return new ResponseEntity<>(tarefa, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefasDTO> update(@PathVariable Long id, @RequestBody TarefasDTO tarefas) {
        TarefasDTO tarefa = this.tarefasService.update(tarefas,id);
        return new ResponseEntity<>(tarefa, HttpStatus.NO_CONTENT);
    }


}
