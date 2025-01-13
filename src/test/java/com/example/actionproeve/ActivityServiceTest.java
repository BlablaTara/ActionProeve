package com.example.actionproeve;

import com.example.actionproeve.models.Activity;
import com.example.actionproeve.services.ActivityService;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class ActivityServiceTest {

    private ActivityService activityService;
    private ObjectMapper objectMapper;

    @TempDir
    Path tempDir;

    private File testFile;

    @BeforeEach
    public void setUp() throws StreamWriteException, DatabindException, IOException {
        objectMapper = new ObjectMapper();
        activityService = new ActivityService(objectMapper);
        // Create a temporary JSON file to avoid altering actual files
        testFile = tempDir.resolve("testActivities.json").toFile();

        // Pre-populate the file with initial activities
        List<Activity> initialActivities = List.of(
                new Activity("test", List.of("20", "30"), "Initial Activity 1", 200, 5, 20, null));
        objectMapper.writeValue(testFile, initialActivities);
    }

    @Test
    void testReadExistingActivites() throws IOException {
        List<Activity> activities = activityService.readActivitiesFromFile(testFile.getPath());
        assertNotNull(activities);
        assertFalse(activities.isEmpty(), "should not be empty");
        Activity foundActivity = activities.stream()
                .filter(activity -> "test".equals(activity.getActivityName())).findAny().orElse(null);

        assertEquals("test", foundActivity.getActivityName());
    }

    @Test
    void testSaveActivityToFile() throws IOException {
        Activity activity = new Activity();
        activity.setActivityName("saveTest");
        activity.setDurations(List.of("10", "20", "30"));
        activity.setInformation("This is a test sample");
        activityService.saveActivity(activity, testFile.getPath());

        List<Activity> activities = activityService.readActivitiesFromFile(testFile.getPath());
        assertEquals(2, activities.size(), "there should be 2 activities now");
        // check that both old and new activity is there
        assertTrue(activities.stream().anyMatch(a -> "test".equals(a.getActivityName())));
        assertTrue(activities.stream().anyMatch(a -> "saveTest".equals((a.getActivityName()))));

    }

}
