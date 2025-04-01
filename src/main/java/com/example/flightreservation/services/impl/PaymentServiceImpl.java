package com.example.flightreservation.services.impl;

import org.springframework.stereotype.Service;

import com.example.flightreservation.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public void chargeCreditCard(String cardNumber, double amount) {
        // Implement the logic to charge the credit card
        // This could involve calling an external payment gateway API
        System.out.println("Charging credit card: " + cardNumber + " for amount: " + amount);
    }
}