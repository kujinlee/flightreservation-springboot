package com.example.flightreservation.services;

import com.example.flightreservation.dto.ReservationRequest;
import com.example.flightreservation.entities.Reservation;

public interface ReservationService {
    Reservation bookFlight(ReservationRequest request);

    Reservation findReservationById(Long reservationId);

    Reservation updateReservation(Reservation reservation);
}