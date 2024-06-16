package org.example.DeliveryService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@TestPropertySource(properties = "spring.config.name=application-delivery")
@SpringBootTest
class DeliveryServiceApplicationTest {

//    @Test
    void contextLoads() {
    }

}
