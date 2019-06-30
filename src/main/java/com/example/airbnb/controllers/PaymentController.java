package com.example.airbnb.controllers;

import java.util.List;

import com.example.airbnb.entities.Payment;
import com.example.airbnb.repositories.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PaymentController
 */

@RestController
@RequestMapping(path="/api")
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    //http://localhost:8080/payments
    @GetMapping(path="/payments", produces="application/json")
    public List<Payment> displayPayments() {
        return paymentRepository.getAllPayments();
    }

    //get payments for booking with ID
    //http://localhost:8080/api/bookings/1/payments
    @GetMapping(path="/bookings/{id}/payments", produces="application/json")
    public List<Payment> displayPaymentsForBooking(@PathVariable("id") int bookingId){
        return paymentRepository.getPaymentsForBooking(bookingId);
    }
    
}