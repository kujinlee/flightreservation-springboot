package com.example.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flightreservation.entities.Flight;
import com.example.flightreservation.repositories.FlightRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Controller
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/findFlights")
    public String showFindFlightsPage() {
        return "findFlights";
    }

    @PostMapping("/findFlights")
    public String displayFindFlights(@RequestParam("from") @NotBlank String from,
                                     @RequestParam("to") @NotBlank String to,
                                     @RequestParam("departureDate") @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate,
                                     Model model) {
        List<Flight> flights = flightRepository.findByDepartureCityAndArrivalCityAndDateOfDeparture(from, to, departureDate);
        model.addAttribute("flights", flights);
        return "displayFlights";
    }
}