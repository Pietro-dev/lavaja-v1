package com.lavaja.saas.backend.controllers;

import com.lavaja.saas.backend.dtos.UsuarioInputDTO;
import com.lavaja.saas.backend.dtos.UsuarioOutputDTO;
import com.lavaja.saas.backend.entities.Usuario;
import com.lavaja.saas.backend.repositories.UsuarioRepository;
import com.lavaja.saas.backend.usecases.usuario.AtualizarUsuario;
import com.lavaja.saas.backend.usecases.usuario.CadastrarUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UsuarioController {
    @Autowired
    CadastrarUsuario cadastrarUsuario;
    @Autowired
    AtualizarUsuario atualizarUsuario;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastro/usuarios")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioInputDTO dto){
        Usuario novoUsuario = cadastrarUsuario.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioOutputDTO(novoUsuario));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioOutputDTO>> listarUsuarios(){
        var usuarios = usuarioRepository.findAll().stream()
                .map(UsuarioOutputDTO::new)
                .toList();
        System.out.println("enpoint sendo acessado");
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioOutputDTO> buscarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return ResponseEntity.ok(new UsuarioOutputDTO(usuario));
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInputDTO dto) {
        var usuarioAtualizado = atualizarUsuario.atualizarUsuario(id, dto);
        return ResponseEntity.ok(new UsuarioOutputDTO(usuarioAtualizado));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
