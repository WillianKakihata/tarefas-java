package com.example.bmTopicosProgramacao7s.tarefas.enuns;

public enum StatusEnum {
    EM_PROCESSO(0),
    INICIO(1),
    FINALIZADO(2);

    int status;
    StatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
