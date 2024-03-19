package ru.mts.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.entity.Animal;
import ru.mts.entity.enums.AnimalType;
import ru.mts.servise.factory.AnimalFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CreateAnimalServiceImpl implements CreateAnimalService {
    @Autowired
    AnimalFactory animalFactory;
    private AnimalType type;

    @Override
    public Map<String, List<Animal>> createAnimals(int n) {
        Map<String, List<Animal>> animals = new HashMap<>();
        Animal animal;
        for (int i = 0; i < n; i++) {
            animal = animalFactory.createRandomAnimal();
            String className = animal.getClass().getSimpleName();
            if (animals.containsKey(className)) {
                animals.get(className).add(animal);
            } else {
                List<Animal> animalList = new ArrayList<>();
                animalList.add(animal);
                animals.put(className, animalList);
            }

        }
        return animals;
    }

    @Override
    public Map<String, List<Animal>> createAnimals() {
        return createAnimals(10);
    }
}
