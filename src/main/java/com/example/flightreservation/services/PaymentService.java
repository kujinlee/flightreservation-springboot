package com.example.flightreservation.services;

public interface PaymentService {
    void chargeCreditCard(String cardNumber, double amount);
}