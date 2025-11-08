package com.example.bm_topicos_programacao7s.tarefas.repository;

import com.example.bm_topicos_programacao7s.tarefas.model.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Long> {
}
