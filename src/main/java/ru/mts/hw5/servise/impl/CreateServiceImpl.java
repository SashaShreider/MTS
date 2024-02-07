package ru.mts.hw5.servise.impl;

import ru.mts.hw5.entity.Animal;
import ru.mts.hw5.factory.AnimalFactory;
import ru.mts.hw5.servise.CreateService;

import java.util.ArrayList;

public class CreateServiceImpl implements CreateService {

    private final AnimalFactory animalFactory = new AnimalFactory();

    @Override
    public ArrayList<Animal> createAnimals(int n) {
        ArrayList<Animal> animals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            animals.add(animalFactory.createRandomAnimal());
        }
        return animals;
    }

    @Override
    public ArrayList<Animal> createAnimals() {
        return createAnimals(10);
    }
}
