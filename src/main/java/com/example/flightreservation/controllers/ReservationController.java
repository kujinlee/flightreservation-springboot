package com.example.flightreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flightreservation.dto.ReservationRequest;
import com.example.flightreservation.entities.Flight;
import com.example.flightreservation.entities.Reservation;
import com.example.flightreservation.services.FlightService;
import com.example.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        Flight flight = flightService.findFlightById(flightId);
        modelMap.addAttribute("flight", flight);
        return "completeReservation";  
    }
   
    @PostMapping("/completeReservation")
    public String completeReservation(@RequestParam("flightId") Long flightId,
                                      @RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName,
                                      @RequestParam("email") String email,
                                      @RequestParam("phone") String phone,
                                      @RequestParam("cardNumber") String cardNumber,
                                      @RequestParam("amount") double amount,
                                      ModelMap modelMap) {
        // Create a ReservationRequest object
        ReservationRequest request = new ReservationRequest();
        request.setFlightId(flightId);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setEmail(email);
        request.setPhone(phone);
        request.setCardNumber(cardNumber); // Set the card number
        request.setAmount(amount); // Set the amount

        // Call the reservation service to book the flight
        Reservation reservation = reservationService.bookFlight(request);

        // Add confirmation message and reservation details to the model
        String msg = String.format("Reservation completed successfully!<br/>Reservation ID: %d<br/>Flight: %s<br/>Passenger: %s %s<br/>Amount: %.2f",
                                   reservation.getId(), reservation.getFlight().getFlightNumber(),
                                   reservation.getPassenger().getFirstName(), reservation.getPassenger().getLastName(),
                                   reservation.getAmount());
        modelMap.addAttribute("msg", msg);
        modelMap.addAttribute("reservation", reservation);

        return "reservationConfirmation";
    }
}