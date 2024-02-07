package ru.mts.hw5.servise.impl;

import ru.mts.hw5.entity.Animal;
import ru.mts.hw5.servise.SearchService;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class SearchServiceImpl implements SearchService {
    @Override
    public ArrayList<String> findLeapYearNames(ArrayList<Animal> animals) {
        ArrayList<String> animalNames = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getBirthDate().isLeapYear()) {
                animalNames.add(animal.getName());
            }
        }
        return animalNames;
    }

    @Override
    public ArrayList<Animal> findOlderAnimal(ArrayList<Animal> animals, int N) {
        ArrayList<Animal> olderAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            //считаем колличество дней между датой рождения и текущем временем и переводим в года.
            if (Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > N) {
                olderAnimals.add(animal);
            }
        }
        return olderAnimals;
    }

    @Override
    public void findDuplicate(ArrayList<Animal> animals) {
        for (int i = 0; i < animals.size() - 1; i++) {
            for (int j = i + 1; j < animals.size(); j++) {
                if (animals.get(i).equals(animals.get(j))) {
                    System.out.println(animals.get(i));
                }
            }
        }
    }
}
