package ru.mts.hw5.servise;

import ru.mts.hw5.entity.Animal;

import java.util.ArrayList;

public interface SearchService {

    /**
     * Поиск всех животных, которые родились в високосный год
     *
     * @param animals Массив животных
     * @return Массив имен ивотных.
     */
    ArrayList<String> findLeapYearNames(ArrayList<Animal> animals);

    /**
     * Поиск всех животных, возраст которых старше N лет
     *
     * @param animals Массив животных
     * @param N       Возраст
     * @return Массив животных, старших N лет
     */
    ArrayList<Animal> findOlderAnimal(ArrayList<Animal> animals, int N);

    /**
     * Найти все дубликаты животных
     *
     * @param animals Массив животных
     * @return Массив животных-дубликатов
     */
    ArrayList<Animal> findDuplicate(ArrayList<Animal> animals);

}
