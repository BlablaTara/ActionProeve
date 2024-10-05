package com.example.actionproeve.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "http://localhost:5173")
public class CalenderController {

    public ResponseEntity<?> showCalender(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("employee") == null) {
            //User is not logged in - redirect to login page
            return ResponseEntity.status(302).header("Location", "/login").build();
        }

        // Fetch bookings from the database and display them in the day-view
        // You can return the view or data here as needed
        // For example: return ResponseEntity.ok(...);

        return ResponseEntity.ok("CalendarView");
    }

    //Laves logik til at gemme bookings

}
