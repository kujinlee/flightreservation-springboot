package com.example.flightreservation.services.impl;

import com.example.flightreservation.dto.ReservationRequest;
import com.example.flightreservation.entities.Flight;
import com.example.flightreservation.entities.Passenger;
import com.example.flightreservation.entities.Reservation;
import com.example.flightreservation.repositories.FlightRepository;
import com.example.flightreservation.repositories.PassengerRepository;
import com.example.flightreservation.repositories.ReservationRepository;
import com.example.flightreservation.services.PaymentService;
import com.example.flightreservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PaymentService paymentService; // Autowire the PaymentService

    @Override
    public Reservation bookFlight(ReservationRequest request) {
        // Fetch the flight entity
        Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow(() -> new RuntimeException("Flight not found"));

        // Charge the credit card
        paymentService.chargeCreditCard(request.getCardNumber(), request.getAmount());

        // Create a new passenger entity
        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getFirstName());
        passenger.setLastName(request.getLastName());
        passenger.setEmail(request.getEmail());
        passenger.setPhone(request.getPhone());
        passengerRepository.save(passenger);

        // Create a new reservation entity
        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedIn(false);
        reservation.setNumberOfBags(0);
        reservation.setCardNumber(request.getCardNumber()); // Set the card number
        reservation.setAmount(request.getAmount()); // Set the amount

        // Save the reservation to the database
        reservationRepository.save(reservation);

        return reservation;
    }

    @Override
    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation); // Return the updated reservation
    }
}