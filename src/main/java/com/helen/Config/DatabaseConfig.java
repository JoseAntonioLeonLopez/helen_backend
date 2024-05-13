package com.helen.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
public class DatabaseConfig {

    private final DataSource dataSource;

    @Value("${database.connection.retry.max-attempts}")
    private int maxRetryAttempts;

    @Value("${database.connection.retry.interval-ms}")
    private long retryIntervalMilliseconds;

    public DatabaseConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initialize() {
        int attempt = 0;
        while (attempt < maxRetryAttempts) {
            try {
                // Intenta obtener una conexión a la base de datos
                JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
                jdbcTemplate.execute("SELECT 1");

                // Si no hay excepciones, la conexión se ha establecido correctamente
                System.out.println("Conexión a la base de datos establecida correctamente.");
                return;
            } catch (Exception ex) {
                // Si hay una excepción, espera y reintenta
                System.out.println("Error al intentar conectar a la base de datos. Reintentando en " + retryIntervalMilliseconds + " milisegundos...");
                attempt++;
                try {
                    TimeUnit.MILLISECONDS.sleep(retryIntervalMilliseconds);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("No se pudo establecer conexión a la base de datos después de " + maxRetryAttempts + " intentos.");
    }
}

