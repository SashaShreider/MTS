package ru.mts.hw3;

import java.util.Random;

public interface CreateAnimalService {
    /**
     * Создание 10 уникальных животных
     */
    default void CreateAnimals() {
        int i = 0;
        while (i++ < 10) {
            CreateRandomAnimal();
        }
    }

    /**
     * Создание одного уникального животного
     */
    default void CreateRandomAnimal() {
        Random random = new Random();
        Animal animal;
        String name = "Name_" + random.nextInt(1, 100);
        double cost = random.nextDouble(10000, 100000);
        animal = switch (random.nextInt(4)) {
            case 0 -> new Cat(name, cost);
            case 1 -> new Dog(name, cost);
            case 2 -> new Shark(name, cost);
            case 3 -> new Wolf(name, cost);
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(4));
        };
        System.out.println(animal);
    }
}
