package ru.mts.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.entity.Animal;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private List<Animal> animals;

    @Autowired
    private CreateAnimalService createAnimalService;

    @PostConstruct
    void init() {
        animals = createAnimalService.createAnimals();
        animals.add(animals.get(3));
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
        return new HashSet<>(animals);
    }

    @Override
    public void printDuplicate() {
        for (Animal animal : findDuplicate()) {
            System.out.println(animal);
        }
    }

    @Override
    public void printAnimals() {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
