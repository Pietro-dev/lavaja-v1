package com.lavaja.saas.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginInputDTO(
        @NotBlank @Email
        String email,
        @NotBlank
        String senha
) {
}
