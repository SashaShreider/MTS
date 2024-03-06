package ru.mts.servise;

import ru.mts.entity.Animal;

import java.util.List;


public interface CreateAnimalService {
    /**
     * Создание 10 случайных животных
     *
     * @return Массив из 10 животных
     */
    List<Animal> createAnimals();

    /**
     * Создание n случайных животных
     *
     * @param n Колличество животных
     * @return Массив животных
     */
    List<Animal> createAnimals(int n);

}
