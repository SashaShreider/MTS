package ru.mts.servise;

import org.springframework.stereotype.Component;
import ru.mts.entity.Animal;

import java.util.List;
import java.util.Map;

@Component
public interface CreateAnimalService {
    /**
     * Создание 10 случайных животных
     *
     * @return Массив из 10 животных
     */
    Map<String, List<Animal>> createAnimals();

    /**
     * Создание n случайных животных
     *
     * @param n Колличество животных
     * @return Массив животных
     */
    Map<String, List<Animal>> createAnimals(int n);

}
