package com.lavaja.saas.backend.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioInputDTO (
        @NotBlank(message = "Insira um nome de usuário!")
        String nome,
        @NotBlank(message = "Crie uma senha válida") @Size(min = 6)
        String senha,
        @NotBlank @Column(unique = true)
        String email
) {}

