INSERT IGNORE INTO role (role, description) VALUES
('ADMIN', 'Rol con permisos de administrador'),
('USER', 'Rol con permisos de usuario');

INSERT IGNORE INTO user (username, name, first_surname, second_surname, gender, email, password, city, id_role) VALUES
('administrador', 'Pepe', 'Leon', 'Lopez', 1, 'admin@example.com', '$2y$10$ReSLLFKW6qa8vtI8prN7o.CljGlRHH2uj5R0cKLIklQYToWCXRKFu', 'Sevilla', 1),
('usuario', 'Lucia', 'Fuentes', 'Leon', 2, 'user@example.com', '$2y$10$ReSLLFKW6qa8vtI8prN7o.CljGlRHH2uj5R0cKLIklQYToWCXRKFu', 'Sevilla', 2);

INSERT IGNORE INTO publication (image, title, description, city, public_id, id_user)
VALUES ('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302888/image_1_ngzypr.jpg','Ejemplo1','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302888/image_1_ngzypr',2),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302888/image_2_ho6zno.jpg','Ejemplo2','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302888/image_2_ho6zno',2),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302887/image_3_b3zklp.jpg','Ejemplo3','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302887/image_3_b3zklp',2),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302887/image_10_kjompm.jpg','Ejemplo4','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302887/image_10_kjompm',2),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302887/image_12_etrjtc.jpg','Ejemplo5','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302887/image_12_etrjtc',2),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302887/image_8_a4kwgh.jpg','Ejemplo6','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302887/image_8_a4kwgh',2),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302887/image_9_zakixf.jpg','Ejemplo7','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302887/image_9_zakixf',2),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302886/image_5_c3s9nl.jpg','Ejemplo8','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302886/image_5_c3s9nl',1),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302886/image_4_tepskg.jpg','Ejemplo9','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302886/image_4_tepskg',1),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302886/image_7_cvamrw.jpg','Ejemplo10','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302886/image_7_cvamrw',1),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302886/image_11_kim4lt.jpg','Ejemplo11','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302886/image_11_kim4lt',1),
('https://res.cloudinary.com/daaqce9rq/image/upload/v1716302886/image_6_jewbou.jpg','Ejemplo12','Descripción de la publicación de ejemplo.','Ciudad de Ejemplo','v1716302886/image_6_jewbou',1);


-- Publicaciones con dos "me gusta" (usuarios 1 y 2)
INSERT IGNORE INTO user_fav (id_user, id_publication) VALUES
(1, 1), (2, 1), -- Publicación 1
(1, 2), (2, 2), -- Publicación 2
(1, 3), (2, 3), -- Publicación 3
(1, 8), (2, 8), -- Publicación 8
(1, 9), (2, 9); -- Publicación 9

-- Publicaciones con un "me gusta" (usuario 1)
INSERT IGNORE INTO user_fav (id_user, id_publication) VALUES
(1, 4), -- Publicación 4
(1, 10); -- Publicación 10

-- Publicaciones con un "me gusta" (usuario 2)
INSERT IGNORE INTO user_fav (id_user, id_publication) VALUES
(2, 5), -- Publicación 5
(2, 11); -- Publicación 11

-- Publicaciones sin "me gusta"
-- (No se insertan registros en user_fav para las siguientes publicaciones)
-- Publicación 6
-- Publicación 7
-- Publicación 12
