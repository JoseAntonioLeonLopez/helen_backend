INSERT IGNORE INTO role (role, description) VALUES
('ADMIN', 'Rol con permisos de administrador'),
('USER', 'Rol con permisos de usuario');

INSERT IGNORE INTO user (username, name, first_surname, second_surname, gender, email, password, city, id_role) VALUES
('administrador', 'Pepe', 'Leon', 'Lopez', 1, 'admin@example.com', '$2y$10$IFh5QANI3jmFHk5zi1Grw.B/3JXwqEAi9wHedoFRUh.2yQ4MV5axu', 'Sevilla', 1),
('usuario', 'Lucia', 'Fuentes', 'Leon', 2, 'user@example.com', '$2y$10$IFh5QANI3jmFHk5zi1Grw.B/3JXwqEAi9wHedoFRUh.2yQ4MV5axu', 'Sevilla', 2);