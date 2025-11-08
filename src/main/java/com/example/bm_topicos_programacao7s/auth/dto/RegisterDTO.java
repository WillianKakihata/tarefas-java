package com.example.bm_topicos_programacao7s.auth.dto;

import com.example.bm_topicos_programacao7s.auth.enuns.UserRoleEnum;

public record RegisterDTO(String login, String password, UserRoleEnum role) {
}
