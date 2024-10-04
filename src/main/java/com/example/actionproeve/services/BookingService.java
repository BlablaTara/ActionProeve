package com.example.actionproeve.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.actionproeve.entities.Booking;
import com.example.actionproeve.repositories.BookingRepository;

@Service
public class BookingService {
    @Autowired BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    // public Booking saveBooking(Booking booking) {
    //     return bookingRepository.saveBooking(booking);
    // }

    // public void deleteBooking(Long id) {
    //     bookingRepository.deletBooking(id);
    // }

}
