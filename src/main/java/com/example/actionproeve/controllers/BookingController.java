package com.example.actionproeve.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.actionproeve.entities.Booking;
import com.example.actionproeve.services.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> findAll() {
        return bookingService.findAll();
    }

    // @PostMapping
    // public Booking createBooking(@RequestBody Booking booking) {
    //     return bookingService.saveBooking(booking);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteBooking(@PathVariable Long id) {
    //     bookingService.deleteBooking(id);
    // }
}
