package com.example.actionproeve.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.actionproeve.entities.Activity;

// Rest - retunerer json-data.
@RestController
// Siger at denne tillader min vue ap adgang til denne backend, men min back-end kører jo på 8080.
// @CrossOrigin (origins = "http://localhost:5173")


public class ActivityController {

    @GetMapping("/activity")
    public Activity getActivity(){
        return new Activity();
    }
}
