package com.example.scheduledev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleDevApplication.class, args);
    }
}
