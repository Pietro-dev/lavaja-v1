package com.lavaja.saas.backend.dtos;

public record UsuarioInputDTO (
        String nome,
        String senha,
        String email
) {}

