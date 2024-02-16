package ru.mts.hw5.impl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.hw5.entity.AbstractAnimal;
import ru.mts.hw5.entity.Animal;
import ru.mts.hw5.entity.Cat;
import ru.mts.hw5.entity.Dog;
import ru.mts.hw5.factory.AnimalFactory;
import ru.mts.hw5.factory.AnimalType;
import ru.mts.hw5.servise.impl.SearchServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ClassTest {

    @DisplayName("Equals test")
    @Nested
    class EqualsTest {
        private static Cat cat;
        private static Cat SameCat;
        private static final AnimalFactory animalFactory = new AnimalFactory();

        @BeforeAll
        static void init() {
            cat = (Cat) animalFactory.createAnimal("cat_name", 100, LocalDate.ofYearDay(2000, 1), AnimalType.CAT);
            SameCat = (Cat) animalFactory.createAnimal("cat_name", 100, LocalDate.ofYearDay(2000, 1), AnimalType.CAT);
        }

        @DisplayName("Проверка рефлективности")
        @Test
        void compareWithItselfTest() {

            assertEquals(cat, cat);
        }

        @DisplayName("Сравнение с эквивалентным объектом")
        @Test
        void compareWithSameTest() {
            assertEquals(cat, SameCat);
        }

        @DisplayName("Сравнение с неэквивалентным объектом")
        @Test
        void compareWithAnotherTest() {
            Cat AnotherCat = new Cat("cat_name", 100, LocalDate.ofYearDay(2000, 1));
            assertEquals(cat, AnotherCat);
        }

        @DisplayName("Проверка симметричености")
        @Test
        void symmetricCompareTest() {
            assertAll(
                    () -> assertEquals(cat, SameCat),
                    () -> assertEquals(SameCat, cat)

            );
        }

        @DisplayName("Сравнение с объектом другого класса с аналогичными полями")
        @Test
        void compareDifferentClassesTest() {
            Dog dog = (Dog) animalFactory.createAnimal("cat_name", 100, LocalDate.ofYearDay(2000, 1), AnimalType.DOG);
            assertNotEquals(cat, dog);
        }

        @DisplayName("Проверка контракта equals и hash")
        @Test
        void compareWithHashCodeTest() {
            assertAll(
                    () -> assertEquals(cat, SameCat),
                    () -> assertEquals(cat.hashCode(), cat.hashCode())
            );
        }
    }

    @DisplayName("SearchService test")
    @Nested
    class SearchServiceImplTest {
        private static ArrayList<Animal> animals;
        private static final SearchServiceImpl searchService = new SearchServiceImpl();
        public static final AnimalFactory animalFactory = new AnimalFactory();

        @BeforeAll
        public static void init() {
            animals = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                animals.add(animalFactory.createAnimal(
                        "name_" + i, //name
                        100 + i, // cost,
                        LocalDate.ofYearDay(2000 + i, 1), // birthDate
                        AnimalType.randomAnimalType()));// type
            }
        }

        @DisplayName("Поиск животных с високосным годом рождения")
        @Test
        void findLeapYearNamesTest() {
            ArrayList<String> actualAnimals = searchService.findLeapYearNames(animals);
            ArrayList<String> exceptAnimals = new ArrayList<>(Arrays.asList(
                    animals.get(0).getName(),
                    animals.get(4).getName(),
                    animals.get(8).getName(),
                    animals.get(12).getName())
            );
            Assertions.assertEquals(exceptAnimals, actualAnimals);
        }

        @DisplayName("Поиск живтоных старше N")
        @ParameterizedTest
        @ValueSource(ints = {1, 20, 30})
        void findOlderAnimalTest(int N) {
            ArrayList<Animal> animals = new ArrayList<>();
            Animal animal;
            for (int i = 1; i < 4; i++) {
                animal = animalFactory.createRandomAnimal();
                animal.setBirthDate(LocalDate.now().minusYears(N + i));
                animals.add(animal);
            }
            assertAll(
                    () -> assertEquals(animals, searchService.findOlderAnimal(animals, N), "Все животные старше N"),
                    () -> assertEquals(animals.subList(1, 3), searchService.findOlderAnimal(animals, N + 1), "Часть животных старше N"),
                    () -> assertTrue(searchService.findOlderAnimal(animals, N + 3).isEmpty(), "Все животные младше N")

            );

        }

        @DisplayName("Поиск животных-дубликатов")
        @Test
        void findDuplicateTest() {
            Animal animal1 = ((AbstractAnimal) animals.get(0)).clone();
            Animal animal2 = ((AbstractAnimal) animals.get(3)).clone();
            animals.add(animal1);
            animals.add(animal2);
            ArrayList<Animal> exceptAnimals = new ArrayList<>(Arrays.asList(animal1, animal2));
            assertEquals(exceptAnimals, searchService.findDuplicate(animals));
        }
    }
}