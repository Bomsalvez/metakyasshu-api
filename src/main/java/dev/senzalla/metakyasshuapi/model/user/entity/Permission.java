package dev.senzalla.metakyasshuapi.model.user.entity;


import org.springframework.security.core.GrantedAuthority;

public class Permission implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "USER";
    }
}
