package ru.mts.servise.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.entity.Animal;
import ru.mts.entity.animals.Cat;
import ru.mts.entity.animals.Dog;
import ru.mts.entity.animals.Shark;
import ru.mts.entity.animals.Wolf;
import ru.mts.entity.enums.AnimalType;
import ru.mts.properies.AnimalProperties;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AnimalFactoryTest {
    @Autowired
    AnimalFactory animalFactory;

    @Autowired
    AnimalProperties animalProperties;

    @Test
    @DisplayName("Создание животного с заданными параметрами")
    public void createAnimalTest() {
        String name = "Name";
        double cost = 500.0;
        LocalDate birthDate = LocalDate.ofYearDay(2020, 1);
        AnimalType type = AnimalType.CAT;

        Animal animal = animalFactory.createAnimal(name, cost, birthDate, type);
        assertNotNull(animal);
        assertTrue(
                animal.getName().equals(name) ||
                        animal.getCost().doubleValue() == cost ||
                        animal.getBirthDate().equals(birthDate) ||
                        animal instanceof Cat
        );

    }

    @DisplayName("Создание рандомного животного")
    @Test
    public void createRandomAnimalTest() {
        Animal animal = animalFactory.createRandomAnimal();

        assertNotNull(animal);

        assertTrue(Arrays.asList(
                        Cat.class,
                        Dog.class,
                        Shark.class,
                        Wolf.class
                ).contains(animal.getClass())
        );

        assertTrue(

                animalProperties.getCatNames().contains(animal.getName()) ||
                        animalProperties.getDogNames().contains(animal.getName()) ||
                        animalProperties.getSharkNames().contains(animal.getName()) ||
                        animalProperties.getWolfNames().contains(animal.getName())
        );
    }

}

