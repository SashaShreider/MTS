package ru.mts.hw5.factory;

import ru.mts.hw5.entity.*;

import java.time.LocalDate;
import java.util.Random;

public class AnimalFactory {
    /**
     * Создание животного определенного типа
     *
     * @param type Тип животного
     * @return Животное определенного типа
     */
    public Animal createAnimal(AnimalType type) {
        return switch (type) {
            case CAT -> new Cat();
            case DOG -> new Dog();
            case SHARK -> new Shark();
            case WOLF -> new Wolf();
        };
    }

    /**
     * Создание животного определенного типа
     *
     * @param name      Имя
     * @param cost      Цена
     * @param birthDate Дата рождения
     * @param type      Тип животного
     * @return Животное определенного типа
     */
    public Animal createAnimal(String name, double cost, LocalDate birthDate, AnimalType type) {

        return switch (type) {
            case CAT -> new Cat(name, cost, birthDate);
            case DOG -> new Dog(name, cost, birthDate);
            case SHARK -> new Shark(name, cost, birthDate);
            case WOLF -> new Wolf(name, cost, birthDate);
        };
    }

    /**
     * Создание случайного животного
     *
     * @return Случайное животное
     */
    public Animal createRandomAnimal() {
        Random random = new Random();

        String randomName = "Name_" + random.nextInt(1, 100);
        double randomCost = random.nextDouble(10000, 100000);
        LocalDate currentDate = LocalDate.now();
        //Создание случайной даты рождения (максимальный возраст 50 лет)
        LocalDate randombirthDate = LocalDate
                .ofEpochDay(random.nextLong(
                        currentDate.minusYears(50).toEpochDay(),
                        currentDate.toEpochDay()));

        return createAnimal(randomName, randomCost, randombirthDate, AnimalType.randomAnimalType());
    }
}