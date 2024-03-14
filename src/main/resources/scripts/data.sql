INSERT IGNORE INTO Rol (rol, description) VALUES
('ADMIN', 'Rol con permisos de administrador'),
('USER', 'Rol con permisos de usuario');

INSERT IGNORE INTO User (name, first_surname, gender, email, password, city, id_rol) VALUES
('Admin', 'Prueba', 1, 'admin@example.com', '$2y$10$IFh5QANI3jmFHk5zi1Grw.B/3JXwqEAi9wHedoFRUh.2yQ4MV5axu', 'Sevilla', 1),
('User', 'Prueba', 1, 'user@example.com', '$2y$10$IFh5QANI3jmFHk5zi1Grw.B/3JXwqEAi9wHedoFRUh.2yQ4MV5axu', 'Sevilla', 2);