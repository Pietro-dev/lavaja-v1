package com.lavaja.saas.backend.controllers;

import com.lavaja.saas.backend.dtos.UsuarioInputDTO;
import com.lavaja.saas.backend.dtos.UsuarioOutputDTO;
import com.lavaja.saas.backend.entities.Usuario;
import com.lavaja.saas.backend.repositories.UsuarioRepository;
import com.lavaja.saas.backend.usecases.usuario.CadastrarUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro/usuarios")
public class UsuarioController {
    @Autowired
    CadastrarUsuario cadastrarUsuario;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioInputDTO dto){
        Usuario novoUsuario = cadastrarUsuario.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioOutputDTO(novoUsuario));
    }
}
