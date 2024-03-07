package ru.mts.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mts.entity.Animal;
import ru.mts.entity.animals.Cat;
import ru.mts.entity.animals.Dog;
import ru.mts.entity.animals.Shark;
import ru.mts.entity.animals.Wolf;
import ru.mts.servise.CreateAnimalService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class AnimalsRepositoryTest {

    @MockBean
    CreateAnimalService createAnimalServiceMock;
    @Autowired
    AnimalsRepositoryImpl animalsRepository;

    @BeforeEach
    void setUp() {
        Map<String, List<Animal>> animals = new HashMap<>();

        animals.put("Cat", new ArrayList<>(List.of(new Cat("catName", 12, LocalDate.ofYearDay(2020, 1)))));
        animals.put("Dog", new ArrayList<>(List.of(new Dog("dogName", 12, LocalDate.ofYearDay(2019, 1)))));
        animals.put("Shark", new ArrayList<>(List.of(new Shark("sharkName", 12, LocalDate.ofYearDay(2018, 1)))));
        Wolf wolf = new Wolf("wolfName", 12, LocalDate.ofYearDay(2021, 1));
        animals.put("Wolf", new ArrayList<>(List.of(wolf, (Wolf) wolf.clone())));

        when(createAnimalServiceMock.createAnimals()).thenReturn(animals);
        animalsRepository.init();

    }

    @Test
    @DisplayName("Животные с високосным годом рождения")
    void FindLeapYearNamesTest() {
        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
        assertEquals(1, leapYearNames.size());
        assertEquals("Cat catName", leapYearNames.keySet().iterator().next());

    }

    @Test
    @DisplayName("Животные старше age")
    void FindOlderAnimalTest() {
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(4);
        assertEquals(2, olderAnimals.size());
        assertTrue(List.of("sharkName", "dogName").contains(olderAnimals.keySet().iterator().next().getName()));
    }

    @Test
    @DisplayName("Животных старше age нет")
    void FindOlderAnimalWhenEveryoneIsYoungerTest() {
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(10);
        assertEquals(1, olderAnimals.size());
        assertEquals(olderAnimals.keySet().iterator().next().getName(), "sharkName");
    }


    @Test
    @DisplayName("Поиск дубликата")
    void FindDuplicateTest() {
        Map<String, Integer> duplicateAnimals = animalsRepository.findDuplicate();
        assertEquals(1, duplicateAnimals.get("Cat"));
        assertEquals(1, duplicateAnimals.get("Dog"));
        assertEquals(1, duplicateAnimals.get("Shark"));
        assertEquals(2, duplicateAnimals.get("Wolf"));
    }

}