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
     * @return список имен животных и исх возраст.
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Поиск всех животных, возраст которых старше N лет
     *
     * @param N Возраст
     * @return Список животных, старших N лет
     */
    Map<Animal, Integer> findOlderAnimal(int N);

    /**
     * Найти все дубликаты животных
     *
     * @return список животных-дубликатов
     */
    Map<String, List<Animal>> findDuplicate();

    /**
     * Вывести список животных
     */
    void printAnimals();

    /**
     * Вывести список животных
     */
    default void printAnimals(List<?> animals) {
        if (animals.isEmpty())
            System.out.println(" cписок пуст");
        else
            animals.forEach(
                    animal -> System.out.println("-" + animal)
            );
    }

    /**
     * Вывести список животных
     */
    default void printAnimals(Map<?, ?> animals) {
        if (animals.isEmpty())
            System.out.println(" cписок пуст");
        else
            animals.forEach(
                    (key, value) -> System.out.println("-" + key + ":  " + value)
            );
    }

    /**
     * Получить список животных
     */
    Map<String, List<Animal>> getAnimals();

    /**
     * Найти средний возраст животных.
     *
     * @return средний возраст животных
     */
    double findAverageAge();

    /**
     * Найти животных старше 5 лет и дороже, чем средняя стоимость всех животных.
     *
     * @return список животных, отсортированный по дате рождения
     */
    List<Animal> findOldAndExpensive();

    /**
     * Найти 3 животных с наименьшей стоимостью.
     *
     * @return список имен животных с наименьшей стоимостью
     */
    List<String> findMinCostAnimals();
}
