package org.example.MemberService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MemberServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-member");
        SpringApplication.run(MemberServiceApplication.class, args);
    }
}
