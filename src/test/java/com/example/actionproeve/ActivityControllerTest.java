package com.example.actionproeve;

import com.example.actionproeve.models.Activity;
import com.example.actionproeve.services.ActivityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Add this annotation
public class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ActivityService activitiesService;

    private ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH = "src/main/resources/activities.json";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSubmitActivity() throws JsonProcessingException, Exception {
        Activity activity = new Activity();
        activity.setActivityName("test");
        activity.setDurations(List.of("10", "20", "30"));
        activity.setInformation("This is a test sample");

        mockMvc.perform(post("/add-activity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(activity)))
                .andExpect(status().isOk());

        List<Activity> activities = readActivitiesFromJsonFile();

        boolean isActivityAdded = activities.stream()
                .anyMatch(a -> "test".equals(a.getActivityName())
                        && List.of(10, 20, 30).equals(a.getDurations())
                        && "This is a test sample".equals(a.getInformation()));

        assertTrue(isActivityAdded, "Activity should be added");
    }

    private List<Activity> readActivitiesFromJsonFile() throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(JSON_FILE_PATH));
        return objectMapper.readValue(jsonData,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Activity.class));
    }
}
