package ru.mts.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.entity.Animal;
import ru.mts.entity.enums.AnimalType;
import ru.mts.servise.factory.AnimalFactory;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateAnimalServiceImpl implements CreateAnimalService {
    @Autowired
    AnimalFactory animalFactory;
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
