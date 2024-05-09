FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 8081
ARG JAR_FILE
COPY target/demo-1.0.0.jar helen_backend.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/helen_backend.jar"]