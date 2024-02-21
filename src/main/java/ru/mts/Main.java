package ru.mts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.mts.servise.AnimalsRepository;

@ComponentScan
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        AnimalsRepository repository = context.getBean(AnimalsRepository.class);
        System.out.println("Исходный список:");
        repository.printAnimals();

        System.out.println("\nИмена животных с високосным годом:");
        System.out.println(repository.findLeapYearNames());


        System.out.println("\nСписок животных, старше 20 лет:");
        System.out.println(repository.findOlderAnimal(20));

        System.out.println("\nСписок дубликатов:");
        repository.findDuplicate();
    }
}
