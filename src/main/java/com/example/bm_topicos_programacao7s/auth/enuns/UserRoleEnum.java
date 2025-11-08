package com.example.bm_topicos_programacao7s.auth.enuns;

public enum UserRoleEnum{
    ADMIN("admin"),
    USER("user");
    private String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
