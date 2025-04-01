package com.example.flightreservation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EntityScan(basePackages = "com.example.flightreservation.entities") // Specifies the package to scan for JPA entities
public class FlightreservationApplication {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(FlightreservationApplication.class, args);
    }

    @PostConstruct
    public void logActiveProfiles() {
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("The following profiles are active: " + String.join(", ", activeProfiles));
    }
}
