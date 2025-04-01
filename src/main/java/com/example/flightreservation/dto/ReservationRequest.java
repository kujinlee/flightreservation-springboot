package com.example.flightreservation.dto;

import lombok.Data;

@Data
public class ReservationRequest {
    private Long flightId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String cardNumber; // Add this field for payment
    private double amount; // Add this field for payment amount
}