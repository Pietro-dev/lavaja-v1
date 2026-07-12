package com.lavaja.saas.backend.dtos;

import com.lavaja.saas.backend.entities.Usuario;

import java.util.Set;

public record UsuarioOutputDTO(
        Long id,
        String nome,
        String email,
        Set<String> roles
) {
    public UsuarioOutputDTO(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRoles()
        );
    }
}
