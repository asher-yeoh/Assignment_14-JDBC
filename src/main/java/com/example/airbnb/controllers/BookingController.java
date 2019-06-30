package com.example.airbnb.controllers;

import java.util.List;

import com.example.airbnb.entities.Booking;
import com.example.airbnb.repositories.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BookingController
 */

@RestController
@RequestMapping(path="/api")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    //http://localhost:8080/api/bookings
    @GetMapping(path="/bookings", produces="application/json")
    public List<Booking> displayBookings() {
        return bookingRepository.getAllBookings();
    }

    //get bookings for property with id
    //http://localhost:8080/api/properties/1/bookings
    @GetMapping(path="/properties/{id}/bookings", produces="application/json")
    public List<Booking> displayBookingsByPropertyId(@PathVariable("id") int propertyId) {
        return bookingRepository.getBookingsByPropertyId(propertyId);
    }

    //update total price for a booking by id: totalPrice
    //http://localhost:8080/api/bookings/1
    @PostMapping(value="/bookings/{id}", produces="application/json")
    //payload = RequestBody
    public Booking updateTotalPriceById(@RequestBody Booking booking, @PathVariable("id") int id) {
        bookingRepository.updateTotalPriceById(booking, id);
        return booking;
    }

}