package org.example.DeliveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DeliveryServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-delivery");
        SpringApplication.run(DeliveryServiceApplication.class, args);
    }
}
