package com.example.bm_topicos_programacao7s.tarefas.model;
import com.example.bm_topicos_programacao7s.tarefas.enuns.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tarefas")
public class Tarefas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulos")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status")
    private StatusEnum status;

}
