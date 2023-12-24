package ru.mts.hw4;

import ru.mts.hw4.classe.AbstractAnimal;
import ru.mts.hw4.classe.Animal;
import ru.mts.hw4.servise.CreateServiceImpl;
import ru.mts.hw4.servise.SearchService;
import ru.mts.hw4.servise.SearchServiceImpl;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CreateServiceImpl createService = new CreateServiceImpl();
        SearchService searchService = new SearchServiceImpl();

        ArrayList<Animal> animals = createService.createAnimals(5);
        Animal animalClone = ((AbstractAnimal) animals.get(0)).clone();
        animals.add(3, animalClone);

        System.out.println("Исходный список:");
        printAnimalList(animals);

        ArrayList<String> leapAniamls = searchService.findLeapYearNames(animals);
        System.out.println("\nИмена животных с високосным годом:");
        System.out.println(leapAniamls);

        System.out.println("\nСписок животных, старше 20 лет:");
        ArrayList<Animal> oldAnimals = searchService.findOlderAnimal(animals, 20);
        printAnimalList(oldAnimals);

        System.out.println("\nСписок дубликатов:");
        searchService.findDuplicate(animals);
    }

    public static void printAnimalList(ArrayList<Animal> els) {
        for (Object el : els) {
            System.out.println(el);
        }
    }
}
