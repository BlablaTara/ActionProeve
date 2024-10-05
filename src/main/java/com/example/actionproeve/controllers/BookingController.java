package com.example.actionproeve.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /*@GetMapping("/day")
    public List<Booking> getBookingsForDay(@RequestParam("date") String date) {
        //Metoden skal lige skrives :)
        return bookingService.getBookingsForDay(LocalDate.parse(date));
    }*/

    // @PostMapping
    // public Booking createBooking(@RequestBody Booking booking) {
    //     return bookingService.saveBooking(booking);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteBooking(@PathVariable Long id) {
    //     bookingService.deleteBooking(id);
    // }
}
