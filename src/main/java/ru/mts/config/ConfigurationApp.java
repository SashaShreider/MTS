package ru.mts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.servise.CreateAnimalService;
import ru.mts.servise.CreateAnimalServiceImpl;

@Configuration
public class ConfigurationApp {
    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }
}
