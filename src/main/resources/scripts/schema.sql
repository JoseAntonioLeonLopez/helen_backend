-- Crear tabla Rol si no existe
CREATE TABLE IF NOT EXISTS Rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    rol VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL
);

-- Crear tabla User si no existe
CREATE TABLE IF NOT EXISTS User (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    first_surname VARCHAR(100) NOT NULL,
    second_surname VARCHAR(100),
    gender TINYINT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15) UNIQUE,
    image_user LONGBLOB,
    city VARCHAR(100) NOT NULL,
    id_rol INT NOT NULL,
    FOREIGN KEY (id_rol) REFERENCES Rol(id_rol)
);

-- Crear tabla User_follow si no existe
CREATE TABLE IF NOT EXISTS User_follow (
    id_user_follow INT AUTO_INCREMENT PRIMARY KEY,
    user_follow INT NOT NULL,
    user_followed INT NOT NULL,
    follow_date DATETIME NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_follow) REFERENCES User(id_user),
    FOREIGN KEY (user_followed) REFERENCES User(id_user),
    CONSTRAINT UNIQUE_FOLLOW UNIQUE (user_follow, user_followed)
);

-- Crear tabla Publication si no existe
CREATE TABLE IF NOT EXISTS Publication (
    id_publication INT AUTO_INCREMENT PRIMARY KEY,
    image LONGBLOB NOT NULL,
    title VARCHAR(50) NOT NULL,
    description TEXT,
    created_date DATETIME NOT NULL DEFAULT NOW(),
    city VARCHAR(100) NOT NULL,
    favorite INT NOT NULL DEFAULT 0,
    id_user INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES User(id_user)
);

-- Crear tabla User_fav si no existe
CREATE TABLE IF NOT EXISTS User_fav (
    id_user_fav INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT NOT NULL,
    id_publication INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES User(id_user),
    FOREIGN KEY (id_publication) REFERENCES Publication(id_publication)
);

-- Crear tabla Comment si no existe
CREATE TABLE IF NOT EXISTS Comment (
    id_comment INT AUTO_INCREMENT PRIMARY KEY,
    comment VARCHAR(255) NOT NULL,
    created_date DATETIME NOT NULL DEFAULT NOW(),
    id_user INT NOT NULL,
    id_publication INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES User(id_user),
    FOREIGN KEY (id_publication) REFERENCES Publication(id_publication)
);
