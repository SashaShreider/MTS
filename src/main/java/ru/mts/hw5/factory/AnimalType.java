package ru.mts.hw5.factory;

import java.util.Random;

public enum AnimalType {
    CAT, DOG, SHARK, WOLF;

    /**
     * Выбор случайного типа
     *
     * @return Случайный тип
     */
    public static AnimalType randomAnimalType() {
        return values()[new Random().nextInt(values().length)];
    }
}
