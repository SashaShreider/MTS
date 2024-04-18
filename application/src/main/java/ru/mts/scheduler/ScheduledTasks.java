package ru.mts.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.entity.enums.AnimalType;
import ru.mts.exceptions.checked.AnimalStreamException;
import ru.mts.exceptions.unchecked.ArgumentException;
import ru.mts.repository.AnimalsRepository;
import ru.mts.servise.factory.AnimalFactory;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
public class ScheduledTasks {

    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    @Autowired
    AnimalFactory animalFactory;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60_000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        try {
            animalFactory.createAnimal(null, 100, LocalDate.now(), AnimalType.CAT);
        } catch (ArgumentException e) {
            System.out.println(e.getClass().getSimpleName() + ":\t" + e.getMessage());
        }
        try {
            animalFactory.createAnimal("catName", -50, LocalDate.now(), AnimalType.CAT);
        } catch (ArgumentException e) {
            System.out.println(e.getClass().getSimpleName() + ":\t" + e.getMessage());
        }
        try {
            animalFactory.createAnimal("catName", 100, null, AnimalType.CAT);
        } catch (ArgumentException e) {
            System.out.println(e.getClass().getSimpleName() + ":\t" + e.getMessage());
        }
        try {
            animalFactory.createAnimal("catName", 100, LocalDate.now().plusDays(1), AnimalType.CAT);
        } catch (ArgumentException e) {
            System.out.println(e.getClass().getSimpleName() + ":\t" + e.getMessage());
        }
        try {
            animalFactory.createAnimal("catName", 100, LocalDate.now(), null);
        } catch (ArgumentException e) {
            System.out.println(e.getClass().getSimpleName() + ":\t" + e.getMessage());
        }

        System.out.println("Исходный список:");
        animalsRepository.printAnimals();

        System.out.println("\nИмена животных с високосным годом:");

        animalsRepository.printAnimals(animalsRepository.findLeapYearNames());

        System.out.println("\nСписок животных, старше 20 лет:");
        try {
            animalsRepository.printAnimals(animalsRepository.findOlderAnimal(20));
        } catch (ArgumentException e) {
            System.out.println(e.getClass().getSimpleName() + ":\t" + e.getMessage());
        }

        System.out.println("\nСписок дубликатов:");
        animalsRepository.printAnimals(animalsRepository.findDuplicate());

        System.out.println("\nСредний возраст:");
        System.out.println(animalsRepository.findAverageAge());

        System.out.println("\nЖивотные, старше 5 лет и дороже средной стоимости:");
        animalsRepository.printAnimals(animalsRepository.findOldAndExpensive());

        System.out.println("\n3 Животных с наименьшей стоимостью:");
        try {
            animalsRepository.printAnimals(animalsRepository.findMinCostAnimals());
        } catch (AnimalStreamException e) {
            System.out.println(e.getClass().getSimpleName() + ":\t" + e.getMessage() + "\n");
            ;
        }


    }
}
