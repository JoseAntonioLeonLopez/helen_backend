-- Crear tabla Role si no existe
CREATE TABLE IF NOT EXISTS role (
    id_role INT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL
);

-- Crear tabla User si no existe
CREATE TABLE IF NOT EXISTS user (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    first_surname VARCHAR(100) NOT NULL,
    second_surname VARCHAR(100),
    gender TINYINT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15) UNIQUE,
    image_user VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    id_role INT NOT NULL DEFAULT 2,
    FOREIGN KEY (id_role) REFERENCES role(id_role)
);

-- Crear tabla User_follow si no existe
CREATE TABLE IF NOT EXISTS user_follow (
    id_user_follow INT AUTO_INCREMENT PRIMARY KEY,
    user_follow INT NOT NULL,
    user_followed INT NOT NULL,
    follow_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_follow) REFERENCES user(id_user),
    FOREIGN KEY (user_followed) REFERENCES user(id_user),
    CONSTRAINT UNIQUE_FOLLOW UNIQUE (user_follow, user_followed)
);

-- Crear tabla Publication si no existe
CREATE TABLE IF NOT EXISTS publication (
    id_publication INT AUTO_INCREMENT PRIMARY KEY,
    image VARCHAR(255) NOT NULL,
    title VARCHAR(50) NOT NULL,
    description TEXT,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    city VARCHAR(100) NOT NULL,
    favorite INT NOT NULL DEFAULT 0,
    id_user INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user(id_user)
);

-- Crear tabla User_fav si no existe
CREATE TABLE IF NOT EXISTS user_fav (
    id_user_fav INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT NOT NULL,
    id_publication INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user(id_user),
    FOREIGN KEY (id_publication) REFERENCES publication(id_publication)
);

-- Crear tabla Comment si no existe
CREATE TABLE IF NOT EXISTS comment (
    id_comment INT AUTO_INCREMENT PRIMARY KEY,
    comment VARCHAR(255) NOT NULL,
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_user INT NOT NULL,
    id_publication INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user(id_user),
    FOREIGN KEY (id_publication) REFERENCES publication(id_publication)
);
