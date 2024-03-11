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
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AnimalsRepositoryTest {

    @MockBean
    CreateAnimalService createAnimalServiceMock;
    @Autowired
    AnimalsRepositoryImpl animalsRepository;

    @BeforeEach
    void setUp() {
        ArrayList<Animal> animals = new ArrayList<>(List.of(
                new Cat("cat", 12, LocalDate.ofYearDay(2020, 1)),
                new Dog("dog", 12, LocalDate.ofYearDay(2019, 1)),
                new Shark("shark", 12, LocalDate.ofYearDay(2018, 1)),
                new Wolf("wolf", 12, LocalDate.ofYearDay(2021, 1))
        ));
        animals.add(animals.get(3));

        when(createAnimalServiceMock.createAnimals()).thenReturn(animals);
        animalsRepository.init();

    }

    @Test
    @DisplayName("Животные с високосным годом рождения")
    void findLeapYearNamesTest() {
        List<String> leapYearNames = animalsRepository.findLeapYearNames();
        assertEquals(1, leapYearNames.size());
        assertEquals("cat", leapYearNames.get(0));
    }

    @Test
    @DisplayName("Животные старше age")
    void findOlderAnimalTest() {
        List<Animal> olderAnimals = animalsRepository.findOlderAnimal(4);
        assertEquals(2, olderAnimals.size());
        assertEquals("dog", olderAnimals.get(0).getName());
        assertEquals("shark", olderAnimals.get(1).getName());
    }


    @Test
    @DisplayName("Поиск дубликата")
    void findDuplicateTest() {
        Set<Animal> duplicateAnimals = animalsRepository.findDuplicate();
        assertEquals(1, duplicateAnimals.size());
        assertEquals("wolf", duplicateAnimals.iterator().next().getName());
    }

}