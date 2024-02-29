package ru.mts.servise.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.mts.properies.AnimalProperties;
import ru.mts.entity.Animal;
import ru.mts.entity.animals.Cat;
import ru.mts.entity.animals.Dog;
import ru.mts.entity.animals.Shark;
import ru.mts.entity.animals.Wolf;
import ru.mts.entity.enums.AnimalType;

import java.time.LocalDate;
import java.util.Random;

@Service
@EnableConfigurationProperties(AnimalProperties.class)
public class AnimalFactory {

    @Autowired
    private AnimalProperties animalProperties;


    /**
     * Создание животного определенного типа
     *
     * @param name      Имя
     * @param cost      Цена
     * @param birthDate Дата рождения
     * @param type      Тип животного
     * @return Животное определенного типа
     */
    public Animal createAnimal(String name, double cost, LocalDate birthDate, AnimalType type) {

        return switch (type) {
            case CAT -> new Cat(name, cost, birthDate);
            case DOG -> new Dog(name, cost, birthDate);
            case SHARK -> new Shark(name, cost, birthDate);
            case WOLF -> new Wolf(name, cost, birthDate);
        };
    }

    public Animal createRandomAnimal(AnimalType type) {
        Random random = new Random();
        double randomCost = random.nextDouble(10000, 100000);
        LocalDate currentDate = LocalDate.now();
        //Создание случайной даты рождения (максимальный возраст 50 лет)
        LocalDate randomBirthDate = LocalDate.ofEpochDay(random.nextLong(
                currentDate.minusYears(50).toEpochDay(),
                currentDate.toEpochDay()));
        String randomName = switch (type) {
            case CAT -> animalProperties.getCatNames().get(random.nextInt(animalProperties.getCatNames().size()));
            case DOG -> animalProperties.getDogNames().get(random.nextInt(animalProperties.getDogNames().size()));
            case SHARK -> animalProperties.getSharkNames().get(random.nextInt(animalProperties.getSharkNames().size()));
            case WOLF -> animalProperties.getWolfNames().get(random.nextInt(animalProperties.getWolfNames().size()));
        };


        return createAnimal(randomName, randomCost, randomBirthDate, type);
    }

    public Animal createRandomAnimal() {
        return createRandomAnimal(AnimalType.randomAnimalType());
    }

}