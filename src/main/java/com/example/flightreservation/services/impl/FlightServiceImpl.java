package com.example.flightreservation.services.impl;

import com.example.flightreservation.entities.Flight;
import com.example.flightreservation.repositories.FlightRepository;
import com.example.flightreservation.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight findFlightById(Long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }
}
