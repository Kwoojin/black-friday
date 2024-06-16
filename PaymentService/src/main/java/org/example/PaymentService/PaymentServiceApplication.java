package org.example.PaymentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-payment");
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
