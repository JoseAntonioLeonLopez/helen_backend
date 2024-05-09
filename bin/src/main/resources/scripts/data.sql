INSERT IGNORE INTO role (role, description) VALUES
('ADMIN', 'Rol con permisos de administrador'),
('USER', 'Rol con permisos de usuario');

INSERT IGNORE INTO user (username, name, first_surname, second_surname, gender, email, password, city, id_role) VALUES
('administrador', 'Pepe', 'Leon', 'Lopez', 1, 'admin@example.com', '$2y$10$ReSLLFKW6qa8vtI8prN7o.CljGlRHH2uj5R0cKLIklQYToWCXRKFu', 'Sevilla', 1),
('usuario', 'Lucia', 'Fuentes', 'Leon', 2, 'user@example.com', '$2y$10$ReSLLFKW6qa8vtI8prN7o.CljGlRHH2uj5R0cKLIklQYToWCXRKFu', 'Sevilla', 2);