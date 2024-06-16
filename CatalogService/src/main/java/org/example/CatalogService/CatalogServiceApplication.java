package org.example.CatalogService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CatalogServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-catalog");
        SpringApplication.run(CatalogServiceApplication.class, args);
    }
}
