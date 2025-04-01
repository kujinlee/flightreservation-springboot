package com.example.flightreservation;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Configuration
public class TestConfig {

    @PostConstruct
    public void loadEnvVariables() {
        System.out.println("Loading environment variables from .env file...");
        try (Stream<String> lines = Files.lines(Paths.get(".env"))) {
            lines.forEach(line -> {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    System.setProperty(parts[0], parts[1]);
                    System.out.println("Loaded: " + parts[0] + "=" + parts[1]);
                }
            });
        } catch (IOException e) {
            System.err.println("Failed to load .env file: " + e.getMessage());
        }
    }
}