package ru.mts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.properies.AnimalProperties;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;
import ru.mts.scheduler.ScheduledTasks;
import ru.mts.servise.CreateAnimalService;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = MTSApplication.class)
@ActiveProfiles("test")
public class MTSApplicationTest {

    @Autowired
    private AnimalProperties animalProperties;

    @Autowired
    private CreateAnimalService createAnimalService;

    @Autowired
    private AnimalsRepositoryImpl animalRepository;

    @Autowired
    private ScheduledTasks scheduledTasks;


    @Test
    @DisplayName("Поднятие контекста")
    void contextLoads() {
        assertNotNull(animalProperties);
        assertNotNull(createAnimalService);
        assertNotNull(animalRepository);
        assertNotNull(scheduledTasks);
    }

    @Test
    @DisplayName("Экземпляры репозитория хранят различных животных")
    void fg(ApplicationContext context) {
        AnimalsRepository a1 = context.getBean(AnimalsRepository.class);
        AnimalsRepository a2 = context.getBean(AnimalsRepository.class);
        assertNotEquals(a1.getAnimals(), a2.getAnimals());
    }

}