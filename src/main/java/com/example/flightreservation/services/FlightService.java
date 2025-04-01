package com.example.flightreservation.services;

import com.example.flightreservation.entities.Flight;

public interface FlightService {
    Flight findFlightById(Long flightId);
}
