package com.vetagenda.vetagenda_api.domain.enums;

public enum UserRole {
    ADMIN("admin"),
    VETERINARIO("veterinario"),
    RECEPCIONISTA("receptionista");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
