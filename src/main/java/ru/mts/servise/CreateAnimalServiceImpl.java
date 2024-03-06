package ru.mts.servise;

import ru.mts.entity.Animal;
import ru.mts.entity.enums.AnimalType;
import ru.mts.factory.AnimalFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    private final AnimalFactory animalFactory = new AnimalFactory();
    private AnimalType type;

    @Override
    public List<Animal> createAnimals(int n) {
        ArrayList<Animal> animals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            animals.add(animalFactory.createRandomAnimal());
        }
        return animals;
    }

    @Override
    public List<Animal> createAnimals() {
        return createAnimals(10);
    }
}
