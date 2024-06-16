package org.example.DeliveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliveryServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-delivery");
        SpringApplication.run(DeliveryServiceApplication.class, args);
    }
}
