package ru.mts.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.servise.CreateAnimalService;
import ru.mts.servise.CreateAnimalServiceImpl;

@TestConfiguration
public class CreateAnimalServiceTestConfig {
    @Bean
    CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }
}
