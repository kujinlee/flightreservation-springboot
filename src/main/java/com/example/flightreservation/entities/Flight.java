package com.example.flightreservation.entities;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "flight") // Ensure the table name matches the database table name exactly
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", nullable = false, length = 20)
    private String flightNumber;

    @Column(name = "operating_airlines", nullable = false, length = 20)
    private String operatingAirlines;

    @Column(name = "departure_city", nullable = false, length = 20)
    private String departureCity;

    @Column(name = "arrival_city", nullable = false, length = 20)
    private String arrivalCity;

    @Column(name = "date_of_departure", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfDeparture;

    @Column(name = "estimated_departure_time", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp estimatedDepartureTime;

    @Column(name = "price", nullable = false)
    private double price;

    // Getters and Setters
}
