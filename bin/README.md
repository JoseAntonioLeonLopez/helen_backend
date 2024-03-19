# Proyecto Helen - Red Social

Este es el repositorio oficial del proyecto **Helen**, una red social desarrollada en Spring Boot para compartir fotos y conectarse con amigos. A continuación, se detallan los pasos necesarios para configurar y ejecutar el proyecto.

## Requisitos del Sistema

- JDK 17.0.10
- Spring Boot 3.2.3
- MySQL 5.7
- Docker (para la configuración de la base de datos)

## Configuración de la Base de Datos MySQL con Docker

Para configurar la base de datos MySQL utilizando Docker, siga estos pasos:

1. Descargue el archivo `docker-compose.yml` del siguiente enlace: [docker-compose.yml](https://drive.google.com/file/d/12A3T__wNXwYp8mVYLTRIMH2CPyrLfRx6/view?usp=sharing).
   
2. Ejecute el siguiente comando en la terminal para iniciar el contenedor de la base de datos:

    ```bash
    docker compose up -d
    ```

## Configuración del Proyecto

Siga estos pasos para configurar y ejecutar el proyecto:

1. Clone el repositorio de GitHub en su máquina local:

    ```bash
    git clone https://github.com/JoseAntonioLeonLopez/helen_backend.git
    ```

2. Abra el proyecto en su IDE preferido.

3. Instale y actualice todas las dependencias del proyecto:

    - Ejecute la opción "Run As" > "Maven install" en su IDE.
    - Seleccione el proyecto y vaya a "Maven" > "Update Project" > "Force Update" para actualizar todas las dependencias.

## Ejecución del Proyecto

Una vez que haya configurado el proyecto, puede ejecutarlo siguiendo estos pasos:

1. Ejecute la aplicación desde su IDE.
2. La aplicación creará automáticamente la base de datos y cargará los valores iniciales.

¡Ahora está listo para utilizar la red social Helen para compartir fotos y conectarse con amigos!

## Contacto

Si tiene alguna pregunta o comentario sobre el proyecto, no dude en contactarnos. ¡Esperamos que disfrute utilizando Helen!
