CREATE TABLE usuario_roles (
   usuario_id BIGINT NOT NULL,
   role VARCHAR(50) NOT NULL,
   CONSTRAINT fk_usuario_roles FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);