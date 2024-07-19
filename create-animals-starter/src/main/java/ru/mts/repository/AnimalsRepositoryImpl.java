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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Scope("prototype")
@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private Map<String, List<Animal>> animals;


    @Autowired
    private CreateAnimalService createAnimalService;

    @PostConstruct
    public void init() {
        animals = createAnimalService.createAnimals(20);
    }

    @Override
    public ConcurrentHashMap<String, LocalDate> findLeapYearNames() {
        return animalStream()
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(
                        animal -> animal.getClass().getSimpleName() + " " + animal.getName(),
                        Animal::getBirthDate,
                        (a1, a2) -> a1,
                        ConcurrentHashMap::new
                ));

    }


    @Override
    public ConcurrentHashMap<Animal, Integer> findOlderAnimal(int age) {
        if (age < 0)
            throw new BoundaryArgumentException("int age", "не может быть отрицательным");
        return animalStream()
                //оставляем животных старше age
                .filter(animal -> animal.getAge() > age)

                .collect(Collectors.collectingAndThen(
                        Collectors.toConcurrentMap(
                                animal -> animal,
                                Animal::getAge,
                                (a1, a2) -> a1,
                                ConcurrentHashMap::new
                        ), result -> !result.isEmpty() ? result
                                //Если список животных пуст, открываем новый поток и ищем самого старого животного
                                : animalStream().min(Comparator.comparing(Animal::getBirthDate)).stream()
                                //преобразуем найденное животное в ConcurrentHashMap
                                .collect(Collectors.toConcurrentMap(
                                        animal -> animal,
                                        animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears(),
                                        (a1, a2) -> a1,
                                        ConcurrentHashMap::new

                                )))
                );

    }

    @Override
    public ConcurrentHashMap<String, List<Animal>> findDuplicate() {
        //создаем map с колличеством животных
        Map<Animal, Long> animalCountMap = animalStream().
                collect(Collectors.groupingBy(
                        animal -> animal,
                        Collectors.counting())
                );
//        оставляем животных -дубликатов
        return new ConcurrentHashMap<>(animalStream()
                .filter(countedAnimal -> animalCountMap.get(countedAnimal) > 1)
                .collect(Collectors.groupingBy(animal -> animal.getClass().getSimpleName()))
        );
    }

    @Override
    public void printAnimals() {
        printAnimals(animals);
    }

    @Override
    public ConcurrentHashMap<String, List<Animal>> getAnimals() {
        return new ConcurrentHashMap<>(animals);
    }


    private Stream<Animal> animalStream() {
        return animals.values().stream().flatMap(List::stream);
    }

    @Override
    public double findAverageAge() {
        return animalStream().mapToInt(Animal::getAge).average().orElse(0);
    }

    @Override
    public CopyOnWriteArrayList<Animal> findOldAndExpensive() {
        double averageCost = animalStream().mapToDouble(animal -> animal.getCost().doubleValue()).average().orElse(0);
        return new CopyOnWriteArrayList<>(animalStream()
                .filter(animal -> animal.getAge() > 5)
                .filter(animal -> animal.getAge() > averageCost)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .toList()
        );
    }

    @Override
    public CopyOnWriteArrayList<String> findMinCostAnimals() throws AnimalStreamException {
        if (animalStream().count() < 3)
            throw new AnimalStreamException("Животных должно быть не менее 3");
        return new CopyOnWriteArrayList<>(animalStream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .toList()
        );
    }
}
