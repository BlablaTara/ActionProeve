package com.example.actionproeve;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // This tells Spring to use the application-test properties
class ActionProeveApplicationTests {

    @Test
    void contextLoads() {
    }

}
