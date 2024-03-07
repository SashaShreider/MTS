package ru.mts.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.repository.AnimalsRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    @Autowired
    private AnimalsRepository animalsRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60_000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        System.out.println("Исходный список:");
        animalsRepository.printAnimals();

        System.out.println("\nИмена животных с високосным годом:");
        System.out.println(animalsRepository.findLeapYearNames());


        System.out.println("\nСписок животных, старше 20 лет:");
        System.out.println(animalsRepository.findOlderAnimal(20));

        System.out.println("\nСписок дубликатов:");
        System.out.println(animalsRepository.findDuplicate());
    }
}