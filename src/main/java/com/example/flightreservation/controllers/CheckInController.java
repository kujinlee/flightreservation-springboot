package com.example.flightreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flightreservation.entities.Reservation;
import com.example.flightreservation.services.ReservationService;

@Controller
public class CheckInController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/searchReservation")
    public String searchReservation() {
        return "searchReservation";
    }

    @GetMapping("/showCheckIn")
    public String showCheckIn(@RequestParam("reservationId") Long reservationId, ModelMap modelMap) {
        Reservation reservation = reservationService.findReservationById(reservationId);
        if (!isAuthorized(reservation)) {
            modelMap.addAttribute("msg", "You are not authorized to access this reservation.");
            return "error";
        }
        modelMap.addAttribute("reservation", reservation);
        return "checkIn";
    }

    @PostMapping("/completeCheckIn")
    public String completeCheckIn(@RequestParam("reservationId") Long reservationId,
                                  @RequestParam("numberOfBags") int numberOfBags,
                                  ModelMap modelMap) {
        Reservation reservation = reservationService.findReservationById(reservationId);
        if (!isAuthorized(reservation)) {
            modelMap.addAttribute("msg", "You are not authorized to access this reservation.");
            return "error";
        }
        reservation.setNumberOfBags(numberOfBags);
        reservation.setCheckedIn(true);
        Reservation updatedReservation = reservationService.updateReservation(reservation);

        String msg = String.format("Check-in completed successfully!<br/>Reservation ID: %d<br/>Number of Bags: %d",
                                   updatedReservation.getId(), updatedReservation.getNumberOfBags());
        modelMap.addAttribute("msg", msg);
        modelMap.addAttribute("reservation", updatedReservation);

        return "checkInConfirmation";
    }

    private boolean isAuthorized(Reservation reservation) {
        // Implement your authorization logic here
        // For example, check if the current user is the owner of the reservation
        // or has the necessary permissions to access it
        return true; // Replace with actual authorization check
    }
}