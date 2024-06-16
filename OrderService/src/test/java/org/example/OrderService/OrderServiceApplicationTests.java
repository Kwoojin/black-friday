package org.example.OrderService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@TestPropertySource(properties = "spring.config.name=application-order")
@SpringBootTest
class OrderServiceApplicationTests {

//    @Test
    void contextLoads() {
    }

}
