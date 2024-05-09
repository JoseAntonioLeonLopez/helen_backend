FROM openjdk:17-jdk-alpine
COPY target/helen_backend-1.0.0.jar helen_backend.jar
ENTRYPOINT ["java", "-jar","helen_backend.jar"]