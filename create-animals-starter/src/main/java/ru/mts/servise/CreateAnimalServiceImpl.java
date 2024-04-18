package ru.mts.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.entity.Animal;
import ru.mts.entity.enums.AnimalType;
import ru.mts.exceptions.unchecked.BoundaryArgumentException;
import ru.mts.servise.factory.AnimalFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CreateAnimalServiceImpl implements CreateAnimalService {
    @Autowired
    AnimalFactory animalFactory;
    private AnimalType type;

    @Override
    public Map<String, List<Animal>> createAnimals(int n) {
        if (n < 1)
            throw new BoundaryArgumentException("int n", "не может быть меньше 1");

        int duplicates = new Random().nextInt(n/3+1);
        List <Animal> animals = new ArrayList<>(n);
        for (int i = 0; i < n-duplicates; i++) {
            animals.add(animalFactory.createRandomAnimal());
        }
        for (int i = 0; i < duplicates; i++) {
            animals.add(animals.get(new Random().nextInt(animals.size())));
        }


        return animals.stream().collect(Collectors.groupingBy(
                animal -> animal.getClass().getSimpleName()
        ));
    }

    @Override
    public Map<String, List<Animal>> createAnimals() {
        return createAnimals(10);
    }
}
