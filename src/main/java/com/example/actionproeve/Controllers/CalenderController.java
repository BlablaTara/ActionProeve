package com.example.actionproeve.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "http://localhost:5173")
public class CalenderController {

    @GetMapping("/calender")
    public void showCalender() {
        //Check om man er logget ind, hvis ikke, så log ind først
        //Hente booking fra databasen så de kan ses i day-view, hvis der trykkes på en dag
    }

    //Laves logik til at gemme bookings

}
