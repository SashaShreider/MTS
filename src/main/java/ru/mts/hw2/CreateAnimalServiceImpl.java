package ru.mts.hw2;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    public void CreateAnimals(int n) {
        for (int i = 0; i < n; i++) {
            CreateRandomAnimal();
        }
    }

    @Override
    public void CreateAnimals() {
        int i = 0;
        do {
            CreateRandomAnimal();
        }
        while (i++ < 10);
    }
}
