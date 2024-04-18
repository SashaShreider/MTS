package ru.mts.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.mts.entity.Animal;
import ru.mts.servise.CreateAnimalService;

import java.time.LocalDate;
import java.time.Period;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, LocalDate> animalNames = new HashMap<>();
        for (String className : animals.keySet()) {
            for (Animal animal : animals.get(className)) {
                if (animal.getBirthDate().isLeapYear()) {
                    animalNames.put(className + " " + animal.getName(), animal.getBirthDate());
                }
            }
        }
        return animalNames;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int age) {
        Map<Animal, Integer> olderAnimals = new HashMap<>();
        Map.Entry<Animal, Integer> olderAnimal = new AbstractMap.SimpleEntry<>(null, -1);
        boolean isEmpty = true;
        for (String className : animals.keySet()) {
            for (Animal animal : animals.get(className)) {

                //считаем колличество дней между датой рождения и текущем временем и переводим в года.
                int currentAge = Period.between(animal.getBirthDate(), LocalDate.now()).getYears();
                if (currentAge > age) {
                    olderAnimals.put(animal, currentAge);
                    isEmpty = false;
                } else if (isEmpty && currentAge > olderAnimal.getValue()) {
                    olderAnimal = new AbstractMap.SimpleEntry<>(animal, currentAge);
                }
            }
        }

        if (olderAnimals.isEmpty()) {
            olderAnimals.put(olderAnimal.getKey(), olderAnimal.getValue());
        }

        return olderAnimals;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        Map<String, Integer> duplicateAnimals = new HashMap<>();
        for (String className : animals.keySet()) {
            duplicateAnimals.put(className, animals.get(className).size());
        }
        return duplicateAnimals;
    }

    @Override
    public void printAnimals(Map<String, List<Animal>> animalMap) {
        for (String className : animalMap.keySet()) {
            for (Animal animal : animals.get(className)) {
                System.out.println("- " + animal);

            }
        }
        System.out.println(animalMap.isEmpty() ? "список пуст" : "");
    }

    @Override
    public void printAnimals() {

        printAnimals(animals);
    }

    @Override
    public Map<String, List<Animal>> getAnimals() {
        return new HashMap<>(animals);
    }
}
