package com.lavaja.saas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lavaja.saas.backend.entities.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
