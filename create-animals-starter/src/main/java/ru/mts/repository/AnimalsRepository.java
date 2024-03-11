package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mts.entity.Animal;

import java.util.List;
import java.util.Set;

@Repository
public interface AnimalsRepository {
    /**
     * Поиск всех животных, которые родились в високосный год
     *
     * @return Массив имен ивотных.
     */
    List<String> findLeapYearNames();

    /**
     * Поиск всех животных, возраст которых старше N лет
     *
     * @param N Возраст
     * @return Массив животных, старших N лет
     */
    List<Animal> findOlderAnimal(int N);

    /**
     * Найти все дубликаты животных
     *
     * @return Массив животных-дубликатов
     */
    Set<Animal> findDuplicate();


    /**
     * Вывести список животных
     */
    void printAnimals(List<Animal> animalList);

    /**
     * Вывести список животных
     */
    void printAnimals();

    /**
     * Вернуть список животных
     */
    List<Animal> getAnimals();
}
