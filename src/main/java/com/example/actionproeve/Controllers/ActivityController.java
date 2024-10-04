package com.example.actionproeve.Controllers;

import com.example.actionproeve.Models.Activity;
import com.example.actionproeve.Services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

// Rest - retunerer json-data.
@RestController
// Siger at denne tillader min vue ap adgang til denne backend, men min back-end kører jo på 8080.
@CrossOrigin (origins = "http://localhost:5173")

public class ActivityController {
    private ActivityService activityService;

    // I stedet for autowired, så injecte igennem konstruktøren. Mere rigtigt - har jeg hørt.
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> getActivity() throws IOException {
        List<Activity> activities = activityService.getActivity();
        return ResponseEntity.ok(activities);
    }


}
