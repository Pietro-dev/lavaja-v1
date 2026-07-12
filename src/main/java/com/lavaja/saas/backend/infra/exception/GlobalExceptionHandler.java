package com.lavaja.saas.backend.infra.exception;

import com.lavaja.saas.backend.dtos.ErroOutputDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice //intercepta erros de todas as controllers
public class GlobalExceptionHandler {
    // trata todos os erros em @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <List<ErroOutputDTO>> lidarComErroValidacao(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors().stream()
                .map(e -> new ErroOutputDTO(e.getField(), e.getDefaultMessage()))
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    // trata erros no banco de dados
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroOutputDTO> lidarComErroBanco() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErroOutputDTO("erro", "Este e-mail já está cadastrado."));
    }
}
