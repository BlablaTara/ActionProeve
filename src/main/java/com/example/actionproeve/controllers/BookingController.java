package com.example.actionproeve.controllers;

import com.example.actionproeve.models.Booking;
import com.example.actionproeve.models.Employee;
import com.example.actionproeve.services.BookingService;
import com.example.actionproeve.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")

public class BookingController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/createBooking")
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/day")
    public ResponseEntity<List<Booking>> getBookingsByDate(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date); // Convert the incoming string to LocalDate
        List<Booking> bookings = bookingService.getBookingsByDate(localDate);
        return ResponseEntity.ok(bookings);
    }

    // save this for later .
    // @PostMapping
    // public Booking createBooking(@RequestBody Booking booking) {
    // Employee employee = employeeService.findById(booking.getEmployeeId())
    // .orElseThrow(() -> new RuntimeException("Employee not found")); // Ensure the
    // employee exists
    // booking.setEmployee(employee); // Set the employee reference
    // return bookingService.createBooking(booking);
    // }
}
