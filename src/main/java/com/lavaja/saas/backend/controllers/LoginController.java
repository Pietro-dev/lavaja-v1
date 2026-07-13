package com.lavaja.saas.backend.controllers;

import com.lavaja.saas.backend.dtos.LoginInputDTO;
import com.lavaja.saas.backend.dtos.LoginOutputDTO;
import com.lavaja.saas.backend.entities.Usuario;
import com.lavaja.saas.backend.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<LoginOutputDTO> efetuarLogin(@RequestBody @Valid LoginInputDTO dto) {
        // envelope que carrega email e senha
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());

        // valida se as credenciais estão corretas (usa o metodo loadUserByUsername da classe userDetails para validar as informações com os registros do banco. A AutenticacaoService implementa userDetails e sobrescreve o metodo loadUserByUsername)
        var authentication = authenticationManager.authenticate(authenticationToken);


        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginOutputDTO(tokenJWT));
    }
}
