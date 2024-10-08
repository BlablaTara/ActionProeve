package com.example.actionproeve;

import com.example.actionproeve.models.Activity;
import com.example.actionproeve.services.ActivityService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test") // This tells Spring to use the application-test properties
public class ActivityServiceTest {
    private ActivityService activityService; // Remove @Autowired
    private ObjectMapper objectMapper;
    private final String JSON_FILE_PATH = "src/main/resources/activities.json";

    @BeforeEach
    void setup() {
        // Use Mockito to mock the ObjectMapper
        objectMapper = Mockito.mock(ObjectMapper.class);
        // Initialize ActivitiesService with the mocked ObjectMapper
        activityService = new ActivityService(objectMapper);
    }

    // need to look up why it needs this- it relates to readValue
    @SuppressWarnings("unchecked")
    @Test
    void testSaveActivity() throws IOException {
        Activity activity = new Activity();
        activity.setActivityName("test");
        activity.setDurations(List.of("10", "20", "30"));
        activity.setInformation("This is a test sample");

        when(activityService.readActivitiesFromFile()).thenReturn(new ArrayList<>());

        List<Activity> existingActivities = activityService.readActivitiesFromFile();

        // Mock the readValue method to return existing activities
        when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(existingActivities);
        doNothing().when(objectMapper).writeValue(any(File.class), anyList());

        activityService.saveActivity(activity);

        verify(objectMapper).writeValue(any(File.class), anyList());

    };
}
