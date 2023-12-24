package ru.mts.hw4.servise;

import ru.mts.hw4.classe.Animal;

import java.util.ArrayList;

public interface CreateService {

    /**
     * Создание n случайных животных
     *
     * @param n Колличество животных
     * @return Массив животных
     */
    ArrayList<Animal> createAnimals(int n);

    /**
     * Создание 10 случайных животных
     *
     * @return Массив из 10 животных
     */
    ArrayList<Animal> createAnimals();
}
