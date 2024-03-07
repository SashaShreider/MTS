package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mts.entity.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface AnimalsRepository {
    /**
     * Поиск всех животных, которые родились в високосный год
     *
     * @return Массив имен ивотных.
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Поиск всех животных, возраст которых старше N лет
     *
     * @param N Возраст
     * @return Массив животных, старших N лет
     */
    Map<Animal, Integer> findOlderAnimal(int N);

    /**
     * Найти все дубликаты животных
     *
     * @return Массив животных-дубликатов
     */
    Map<String, Integer> findDuplicate();


    /**
     * Вывести список животных
     */
    void printAnimals(Map<String, List<Animal>> animalMap);

    /**
     * Вывести список животных
     */
    void printAnimals();

    /**
     * Вернуть список животных
     */
    Map<String, List<Animal>> getAnimals();
}
