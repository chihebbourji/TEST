package com.pfe.backend.dao.enums;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleEnum implements GrantedAuthority {
    USER_ROLE("Utilisateur"),ADMIN_ROLE("Administrateur");
    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
