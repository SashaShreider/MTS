package ru.mts.servise;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.config.CreateAnimalServiceTestConfig;
import ru.mts.entity.Animal;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = CreateAnimalServiceTestConfig.class)
@ActiveProfiles("test")
class CreateAnimalServiceTest {

    @Autowired
    private CreateAnimalService createAnimalService;

    @Test
    @DisplayName("Проверка уникальности созданных животных")
    void CreateUniqueAnimalsTest() {
        Map<String, List<Animal>> animals = createAnimalService.createAnimals();
        assertEquals(new HashSet<>(animals.values()).size(), animals.values().size());
    }
}