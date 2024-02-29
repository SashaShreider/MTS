package ru.mts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class MTSApplication {
    public static void main(String[] args) {

        SpringApplication.run(MTSApplication.class, args);
    }
}