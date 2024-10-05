package com.example.actionproeve.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.actionproeve.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAll();

    // Object deleteBooking(Long id);

    // Booking saveBooking(Booking booking);

}
