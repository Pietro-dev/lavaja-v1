INSERT INTO usuarios (nome, email, senha)
VALUES ('Admin','admin@lavaja.com','$2a$10$10EBzdnM6aDdmo5Oc42WM.R.k70NNpEA2WbxpP3e9Op43GMchbJty');

INSERT INTO usuario_roles (usuario_id, role)
SELECT id, 'ROLE_ADMIN'
FROM usuarios
WHERE email = 'admin@lavaja.com';