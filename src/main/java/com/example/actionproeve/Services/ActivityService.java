package com.example.actionproeve.Services;

import com.example.actionproeve.Models.Activity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ActivityService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file = new File("src/main/resources/assets/activities.json");

    // Læser json filen fraback-end
    public List<Activity> getActivity() throws IOException {
        return Arrays.asList(objectMapper.readValue(file, Activity[].class));
    }


}
