package ru.mts.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.mts.entity.Animal;
import ru.mts.exceptions.checked.AnimalStreamException;
import ru.mts.exceptions.unchecked.BoundaryArgumentException;
import ru.mts.servise.CreateAnimalService;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Scope("prototype")
@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private Map<String, List<Animal>> animals;


    @Autowired
    private CreateAnimalService createAnimalService;

    @PostConstruct
    void init() {
        animals = createAnimalService.createAnimals();
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        return animalStream()
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(
                        animal -> animal.getClass().getSimpleName() + " " + animal.getName(),
                        Animal::getBirthDate));

    }


    @Override
    public Map<Animal, Integer> findOlderAnimal(int age) {
        if (age < 0)
            throw new BoundaryArgumentException("int age", "не может быть отрицательным");
        return animalStream()
                //оставляем животных старше age
                .filter(animal -> animal.getAge() > age)

                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                animal -> animal,
                                Animal::getAge
                        ), result -> !result.isEmpty() ? result
                                //Если список животных пуст, открываем новый поток и ищем самого старого животного
                                : animalStream().min(Comparator.comparing(Animal::getBirthDate)).stream()
                                //преобразуем найденное животное в HashMap
                                .collect(Collectors.toMap(
                                        animal -> animal,
                                        animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears())))
                );

    }

    @Override
    public Map<String, List<Animal>> findDuplicate() {
        //создаем map с колличеством животных
        Map<Animal, Long> animalCountMap = animalStream().
                collect(Collectors.groupingBy(
                        animal -> animal,
                        Collectors.counting())
                );
//        оставляем животных -дубликатов
        return animalStream()
                .filter(countedAnimal -> animalCountMap.get(countedAnimal) > 1)
                .collect(Collectors.groupingBy(
                        animal -> animal.getClass().getSimpleName()
                ));

    }

    @Override
    public void printAnimals() {
        printAnimals(animals);
    }

    @Override
    public Map<String, List<Animal>> getAnimals() {
        return new HashMap<>(animals);
    }


    private Stream<Animal> animalStream() {
        return animals.values().stream().flatMap(List::stream);
    }

    @Override
    public double findAverageAge() {
        return animalStream().mapToInt(Animal::getAge).average().orElse(0);
    }

    @Override
    public List<Animal> findOldAndExpensive() {
        double averageCost = animalStream().mapToDouble(animal -> animal.getCost().doubleValue()).average().orElse(0);
        return animalStream()
                .filter(animal -> animal.getAge() > 5)
                .filter(animal -> animal.getAge() > averageCost)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .toList();
    }

    @Override
    public List<String> findMinCostAnimals() throws AnimalStreamException {
        if (animalStream().count() < 3)
            throw new AnimalStreamException("Животных должно быть не менее 3");
        return animalStream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}
