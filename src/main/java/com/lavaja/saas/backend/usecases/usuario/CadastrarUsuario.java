package com.lavaja.saas.backend.usecases.usuario;

import com.lavaja.saas.backend.dtos.UsuarioInputDTO;
import com.lavaja.saas.backend.entities.Usuario;
import com.lavaja.saas.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CadastrarUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario save(UsuarioInputDTO dto) {
        Usuario novoUsuario = new Usuario();

        novoUsuario.setNome(dto.nome());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setSenha(passwordEncoder.encode(dto.senha()));

        novoUsuario.getRoles().add("ROLE_CLIENTE");

        return usuarioRepository.save(novoUsuario);
    }
}
