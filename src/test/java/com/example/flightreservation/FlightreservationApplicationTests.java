package com.example.flightreservation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "file:.env") // Explicitly load environment variables from .env file
class FlightreservationApplicationTests {

    @Test
    void contextLoads() {
        // Test to ensure the Spring context loads successfully
    }
}
