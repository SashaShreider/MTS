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
import ru.mts.exceptions.checked.AnimalStreamException;
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

        animals.put("Cat", new ArrayList<>(List.of(new Cat("catName", 10, LocalDate.ofYearDay(2020, 1)))));
        animals.put("Dog", new ArrayList<>(List.of(new Dog("dogName", 8, LocalDate.ofYearDay(2019, 1)))));
        animals.put("Shark", new ArrayList<>(List.of(new Shark("sharkName", 6, LocalDate.ofYearDay(2013, 1)))));
        Wolf wolf = new Wolf("wolfName", 4, LocalDate.ofYearDay(2021, 1));
        //добавляем дубликат
        animals.put("Wolf", new ArrayList<>(List.of(wolf, (Wolf) wolf.clone())));

        when(createAnimalServiceMock.createAnimals()).thenReturn(animals);
        animalsRepository.init();

    }

    @Test
    @DisplayName("Животные с високосным годом рождения")
    void findLeapYearNamesTest() {
        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
        assertEquals(1, leapYearNames.size());
        assertEquals("Cat catName", leapYearNames.keySet().iterator().next());

    }

    @Test
    @DisplayName("Животные старше age")
    void findOlderAnimalTest() {
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(4);

        assertEquals(2, olderAnimals.size());
        assertTrue(List.of("sharkName", "dogName").contains(olderAnimals.keySet().iterator().next().getName()));
    }

    @Test
    @DisplayName("Животных старше age нет")
    void findOlderAnimalWhenEveryoneIsYoungerTest() {
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(10);
        assertEquals(1, olderAnimals.size());
        assertEquals(olderAnimals.keySet().iterator().next().getName(), "sharkName");
    }


    @Test
    @DisplayName("Поиск дубликата")
    void findDuplicateTest() {
        Map<String, List<Animal>> duplicateAnimals = animalsRepository.findDuplicate();
//        animalsRepository.printAnimals(duplicateAnimals);
        assertEquals(1, duplicateAnimals.size());
        assertEquals(2, duplicateAnimals.get("Wolf").size());
    }

    @Test
    @DisplayName("Найти средний возраст животных")
    void findAverageAgeTest() {
        assertEquals((double) (4 + 11 + 3 + 3 + 5) / 5, animalsRepository.findAverageAge());
    }

    @Test
    @DisplayName("Найти животных старше 5 лет и дороже, чем средняя стоимость всех животных")
    void findOldAndExpensiveTest() {
        assertEquals(1, animalsRepository.findOldAndExpensive().size());
        assertEquals("sharkName", animalsRepository.findOldAndExpensive().get(0).getName());
    }

    @Test
    @DisplayName("Найти 3 животных с наименьшей стоимостью")
    void findMinCostAnimalsTest() throws AnimalStreamException {
        assertEquals(List.of("wolfName", "wolfName", "sharkName"), animalsRepository.findMinCostAnimals());
    }

}