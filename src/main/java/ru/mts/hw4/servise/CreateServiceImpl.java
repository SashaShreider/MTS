package ru.mts.hw4.servise;

import ru.mts.hw4.classe.Animal;
import ru.mts.hw4.factory.AnimalFactory;

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
