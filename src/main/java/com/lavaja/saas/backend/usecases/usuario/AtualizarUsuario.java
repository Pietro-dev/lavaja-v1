package com.lavaja.saas.backend.usecases.usuario;

import com.lavaja.saas.backend.dtos.UsuarioInputDTO;
import com.lavaja.saas.backend.entities.Usuario;
import com.lavaja.saas.backend.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuario {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public Usuario atualizarUsuario(Long id, UsuarioInputDTO dto) {
        var usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Se a senha vier no DTO, precisamos codificar
        String senhaHash = encoder.encode(dto.senha());

        usuario.atualizarUsuario(dto, senhaHash);
        return repository.save(usuario);
    }
}
