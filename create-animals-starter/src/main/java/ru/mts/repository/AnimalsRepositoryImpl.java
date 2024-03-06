package ru.mts.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.mts.entity.Animal;
import ru.mts.servise.CreateAnimalService;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Scope("prototype")
@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private List<Animal> animals;

    @Autowired
    private CreateAnimalService createAnimalService;

    @PostConstruct
    void init() {
        animals = createAnimalService.createAnimals();
    }

    @Override
    public List<String> findLeapYearNames() {
        ArrayList<String> animalNames = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getBirthDate().isLeapYear()) {
                animalNames.add(animal.getName());
            }
        }
        return animalNames;
    }

    @Override
    public List<Animal> findOlderAnimal(int age) {
        ArrayList<Animal> olderAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            //считаем колличество дней между датой рождения и текущем временем и переводим в года.
            if (Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > age) {
                olderAnimals.add(animal);
            }
        }
        return olderAnimals;
    }

    @Override
    public Set<Animal> findDuplicate() {
        Set<Animal> duplicateAnimals = new HashSet<>();
        for (int i = 0; i < animals.size() - 1; i++) {
            for (int j = i + 1; j < animals.size(); j++) {
                if (animals.get(i).equals(animals.get(j))) {
                    duplicateAnimals.add(animals.get(i));
                }
            }
        }
        return duplicateAnimals;
    }

    @Override
    public void printAnimals(List<Animal> animalList) {
        for (Animal animal : animalList) {
            System.out.println("- " + animal);
        }
        System.out.println(animalList.isEmpty() ? "список пуст" : "");
    }

    @Override
    public void printAnimals() {

        printAnimals(animals);
    }

    @Override
    public List<Animal> getAnimals() {
        return new ArrayList<Animal>(animals);
    }
}
