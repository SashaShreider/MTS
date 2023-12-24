package ru.mts.hw4.servise;

import ru.mts.hw4.classe.Animal;

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
     * Вывести все дубликаты животных
     *
     * @param animals Массив животных
     */
    void findDuplicate(ArrayList<Animal> animals);

}
